package com.kunji.mijawharati.ui.screens.products

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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.kunji.mijawharati.navigation.ROUT_ADD_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_EDIT_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.mijawharati.navigation.ROUT_ADMINDASHBOARD
import com.kunji.mijawharati.navigation.editProductRoute
import com.kunji.mijawharati.viewmodel.ProductViewModel
import java.io.IOException
import java.io.OutputStream

// App colors
private val EmeraldGreen = Color(0xFF006A4E)
private val CreamWhite = Color(0xFFFCFCF7)
private val CardWhite = Color(0xFFFFFFFF)
private val SoftOverlay = Color(0x88000000)

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductViewModel) {
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
                    title = {
                        Text(
                            "Products",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldGreen
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(ROUT_ADMINDASHBOARD) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = EmeraldGreen
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = CreamWhite),
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = EmeraldGreen
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Product List") },
                                onClick = {
                                    navController.navigate(ROUT_PRODUCT_LIST)
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Add Product") },
                                onClick = {
                                    navController.navigate(ROUT_ADD_PRODUCT)
                                    showMenu = false
                                }
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    placeholder = { Text("Search products...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = EmeraldGreen
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreen,
                        unfocusedBorderColor = EmeraldGreen.copy(alpha = 0.35f),
                        focusedLabelColor = EmeraldGreen,
                        unfocusedLabelColor = EmeraldGreen.copy(alpha = 0.6f),
                        cursorColor = EmeraldGreen,
                        errorBorderColor = Color.Red
                    ),
                    shape = RoundedCornerShape(14.dp)
                )
            }
        },
        bottomBar = { BottomNavigationBar1(navController) },
        containerColor = CreamWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            if (filteredProducts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "No items",
                            tint = EmeraldGreen,
                            modifier = Modifier.size(56.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("No products yet", color = EmeraldGreen, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Tap Add to create a new product.", color = EmeraldGreen.copy(alpha = 0.8f))
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredProducts) { product ->
                        ProductItem(navController, product, viewModel)
                    }
                }
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
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Product Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.BottomStart)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, SoftOverlay)
                            )
                        )
                )
            }

            // Product Name & Price
            Column(
                modifier = Modifier
                    .background(CardWhite)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = EmeraldGreen
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Ksh ${product.price}",
                    fontSize = 14.sp,
                    color = EmeraldGreen.copy(alpha = 0.85f)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Row with Edit, Delete, Download
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.navigate(editProductRoute(product.id)) }
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = EmeraldGreen)
                    }
                    IconButton(
                        onClick = { viewModel.deleteProduct(product) }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = EmeraldGreen)
                    }
                    IconButton(
                        onClick = { generateProductPDF(context, product) }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.download),
                            contentDescription = "PDF",
                            tint = EmeraldGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Message Seller Button (full width)
                OutlinedButton(
                    onClick = {
                        val smsIntent = Intent(Intent.ACTION_SENDTO)
                        smsIntent.data = "smsto:${product.phone}".toUri()
                        smsIntent.putExtra("sms_body", "Hello Seller,...?")
                        context.startActivity(smsIntent)
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldGreen),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Message", tint = EmeraldGreen)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Message Seller", color = EmeraldGreen)
                }
            }
        }
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

@Composable
fun BottomNavigationBar1(navController: NavController) {
    NavigationBar(
        containerColor = EmeraldGreen,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Product List", tint = Color.White) },
            label = { Text("Home", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PRODUCT) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Product", tint = Color.White) },
            label = { Text("Add", color = Color.White) }
        )
    }
}
