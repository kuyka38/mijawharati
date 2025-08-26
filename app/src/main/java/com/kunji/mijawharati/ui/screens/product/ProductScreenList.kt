package com.kunji.mijawharati.ui.screens.products

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.R
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController, viewModel: ProductViewModel) {
    val productList by viewModel.allProducts.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val bottomSheetState = androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    // ✅ Local cart badge count (increments when Add to Cart is tapped)
    var cartCount by remember { mutableStateOf(0) }

    val filteredProducts = productList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(" All Products", fontSize = 20.sp, color = CreamWhite) },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(ROUT_LANDING) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = CreamWhite
                            )
                        }
                    },
                    actions = {
                        // ✅ Cart icon with badge
                        IconButton(onClick = { navController.navigate(ROUT_CART) }) {
                            BadgedBox(
                                badge = {
                                    if (cartCount > 0) {
                                        Badge {
                                            Text(cartCount.toString(), color = CreamWhite)
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = "Cart",
                                    tint = CreamWhite
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = EmeraldGreen)
                )

                // ✅ Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    placeholder = { Text("Search products...", color = Color.Gray) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = EmeraldGreen
                        )
                    }
                )
            }
        },
        bottomBar = { BottomNavigationBar1(navController) },
        containerColor = CreamWhite
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    onClick = {
                        selectedProduct = product
                        scope.launch { bottomSheetState.show() }
                    },
                    viewModel = viewModel
                )
            }
        }
    }

    // ✅ Bottom Sheet for Product Details (90% screen height)
    if (selectedProduct != null) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                    if (!bottomSheetState.isVisible) selectedProduct = null
                }
            },
            sheetState = bottomSheetState,
            containerColor = Color.White,
            modifier = Modifier.fillMaxHeight(0.9f) // ✅ same height as EarringsScreen
        ) {
            ProductDetailContent(
                product = selectedProduct!!,
                viewModel = viewModel,
                onCartAdded = { cartCount++ } // ✅ increment badge when adding
            )
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit, viewModel: ProductViewModel) {
    val painter: Painter = rememberAsyncImagePainter(
        model = product.imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Column(horizontalAlignment = Alignment.Start) {
                Image(
                    painter = painter,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text(
                        text = product.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                    Text(
                        text = "Ksh${product.price}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text("⭐ 4.7", fontSize = 14.sp, color = Color.Gray)
                }
            }

            // ✅ Favorite Icon on top-right of card
            IconButton(
                onClick = { viewModel.toggleFavorite(product) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (product.isFavorite) EmeraldGreen else Color.Gray
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductDetailContent(
    product: Product,
    viewModel: ProductViewModel,
    onCartAdded: () -> Unit // ✅ callback to update badge
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ✅ Image
        val painter: Painter = rememberAsyncImagePainter(
            model = product.imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY
        )
        Image(
            painter = painter,
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        Text(text = product.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = EmeraldGreen)
        Text(text = "Ksh${product.price}", fontSize = 16.sp, color = Color.Gray)

        // ✅ Icons Row (Download + Favorite)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { generateProductPDF(context, product) }) {
                Icon(
                    painter = painterResource(R.drawable.download),
                    contentDescription = "Download PDF",
                    tint = EmeraldGreen
                )
            }

            IconButton(onClick = { viewModel.toggleFavorite(product) }) {
                Icon(
                    imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (product.isFavorite) EmeraldGreen else Color.Gray
                )
            }
        }

        // ✅ Add to Cart button below and wide
        androidx.compose.material3.Button(
            onClick = {
                viewModel.addToCart(product)
                onCartAdded() // ✅ update the badge count
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = CreamWhite)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Add to Cart", color = CreamWhite)
        }
    }
}

@Composable
fun BottomNavigationBar1(navController: NavController) {
    NavigationBar(
        containerColor = EmeraldGreen,
        contentColor = CreamWhite
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_LANDING) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Product List", tint = CreamWhite) },
            label = { Text("Home", color = CreamWhite) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_FAVORITES) },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites", tint = CreamWhite) },
            label = { Text("Favorites", color = CreamWhite) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_CART) },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = CreamWhite) },
            label = { Text("Cart", color = CreamWhite) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun generateProductPDF(context: Context, product: Product) {
    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(300, 500, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas
    val paint = android.graphics.Paint()

    val bitmap: Bitmap? = try {
        product.imagePath?.let {
            val uri = Uri.parse(it)
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    bitmap?.let {
        val scaledBitmap = Bitmap.createScaledBitmap(it, 250, 150, false)
        canvas.drawBitmap(scaledBitmap, 25f, 20f, paint)
    }

    paint.textSize = 16f
    paint.isFakeBoldText = true
    canvas.drawText("Product Details", 80f, 200f, paint)

    paint.textSize = 12f
    paint.isFakeBoldText = false
    canvas.drawText("Name: ${product.name}", 50f, 230f, paint)
    canvas.drawText("Price: Ksh${product.price}", 50f, 250f, paint)
    canvas.drawText("Seller Phone: ${product.phone}", 50f, 270f, paint)

    pdfDocument.finishPage(page)

    val fileName = "${product.name}_Details.pdf"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    val contentResolver = context.contentResolver
    val uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

    if (uri != null) {
        try {
            val outputStream: OutputStream? = contentResolver.openOutputStream(uri)
            if (outputStream != null) {
                pdfDocument.writeTo(outputStream)
                Toast.makeText(context, "PDF saved to Downloads!", Toast.LENGTH_LONG).show()
            }
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to save PDF!", Toast.LENGTH_LONG).show()
        }
    } else {
        Toast.makeText(context, "Failed to create file!", Toast.LENGTH_LONG).show()
    }

    pdfDocument.close()
}
