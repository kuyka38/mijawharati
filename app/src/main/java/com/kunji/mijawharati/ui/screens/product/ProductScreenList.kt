package com.kunji.mijawharati.ui.screens.products

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
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
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.R
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_EDIT_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.mijawharati.navigation.ROUT_PROFILE
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ProductViewModel
import java.io.IOException
import java.io.OutputStream

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController, viewModel: ProductViewModel) {
    val productList by viewModel.allProducts.observeAsState(emptyList())
    var showMenu by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts = productList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Products", fontSize = 20.sp, color = CreamWhite) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = EmeraldGreen),
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = CreamWhite
                            )
                        }
                    }
                )

                // Search Bar
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredProducts) { product ->
                ProductItem(navController, product, viewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductItem(navController: NavController, product: Product, viewModel: ProductViewModel) {
    val painter: Painter = rememberAsyncImagePainter(
        model = product.imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY
    )
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (product.id != 0) {
                    navController.navigate(ROUT_EDIT_PRODUCT)
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                // Product Image
                Image(
                    painter = painter,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Product Info
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
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Buttons Column (Message + Add to Cart + Download)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // ✅ Message Seller Button
                    Button(
                        onClick = {
                            val smsIntent = Intent(Intent.ACTION_SENDTO)
                            smsIntent.data = "smsto:${product.phone}".toUri()
                            smsIntent.putExtra(
                                "sms_body",
                                "Hello Seller, I'm interested in ${product.name}."
                            )
                            context.startActivity(smsIntent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Message Seller",
                            tint = CreamWhite,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Message Seller", color = CreamWhite, fontSize = 14.sp)
                    }

                    // ✅ Add to Cart Button
                    Button(
                        onClick = {  viewModel.addToCart(product) },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Add to Cart",
                            tint = CreamWhite,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Add to Cart", color = CreamWhite, fontSize = 14.sp)
                    }

                    // ✅ Download PDF Button
                    IconButton(onClick = { generateProductPDF(context, product) }) {
                        Icon(
                            painter = painterResource(R.drawable.download),
                            contentDescription = "Download PDF",
                            tint = EmeraldGreen
                        )
                    }
                }
            }

            // ✅ Favorite Button updates Room database
            IconButton(
                onClick = { viewModel.toggleFavorite(product) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .background(Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(50))
            ) {
                Icon(
                    imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (product.isFavorite) Color.Red else EmeraldGreen
                )
            }
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
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
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
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PROFILE) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = CreamWhite) },
            label = { Text("Profile", color = CreamWhite) }
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
