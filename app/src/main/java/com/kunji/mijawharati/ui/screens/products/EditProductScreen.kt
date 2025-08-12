package com.kunji.mijawharati.ui.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.navigation.ROUT_ADD_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.mijawharati.viewmodel.ProductViewModel

// MiJawharati Theme Colors
private val EmeraldGreen = Color(0xFF006A4E)
private val CreamWhite = Color(0xFFFCFCF7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(productId: Int?, navController: NavController, viewModel: ProductViewModel) {
    val context = LocalContext.current
    val productList by viewModel.allProducts.observeAsState(emptyList())

    val product = remember(productList) { productList.find { it.id == productId } }

    var name by remember { mutableStateOf(product?.name ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(product?.imagePath ?: "") }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imagePath = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        containerColor = CreamWhite,
        topBar = {
            TopAppBar(
                title = { Text("Edit Product", color = CreamWhite) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = EmeraldGreen),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = CreamWhite)
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = CreamWhite)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
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
        },
        bottomBar = { BottomNavigationBar2(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CreamWhite)
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (product != null) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Product Name", color = EmeraldGreen) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreen,
                        unfocusedBorderColor = EmeraldGreen.copy(alpha = 0.5f),
                        focusedLabelColor = EmeraldGreen
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Product Price", color = EmeraldGreen) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreen,
                        unfocusedBorderColor = EmeraldGreen.copy(alpha = 0.5f),
                        focusedLabelColor = EmeraldGreen
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(imagePath)),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(8.dp)
                )

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(EmeraldGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Change Image", color = CreamWhite, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val updatedPrice = price.toDoubleOrNull()
                        if (updatedPrice != null) {
                            viewModel.updateProduct(product.copy(name = name, price = updatedPrice, imagePath = imagePath))
                            Toast.makeText(context, "Product Updated!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Invalid price entered!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    colors = ButtonDefaults.buttonColors(EmeraldGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Update Product", color = CreamWhite, fontWeight = FontWeight.Bold)
                }
            } else {
                Text(
                    text = "Product not found",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(EmeraldGreen)
                ) {
                    Text("Go Back", color = CreamWhite)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar2(navController: NavController) {
    NavigationBar(
        containerColor = EmeraldGreen,
        contentColor = CreamWhite
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Product List", tint = CreamWhite) },
            label = { Text("Products", color = CreamWhite) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PRODUCT) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Add Product", tint = CreamWhite) },
            label = { Text("Add", color = CreamWhite) }
        )
    }
}
