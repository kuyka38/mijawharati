package com.kunji.mijawharati.ui.screens.products

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_ADD_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.swaggy.viewmodel.ProductViewModel

// Aesthetic colors
val EmeraldGreen = (com.kunji.mijawharati.ui.theme.EmeraldGreen)
val CreamWhite = (com.kunji.mijawharati.ui.theme.CreamWhite)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, viewModel: ProductViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        containerColor = CreamWhite,
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Product", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
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
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Product Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Product Name") },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.name), contentDescription = "name")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreen,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = EmeraldGreen
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Price
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.price), contentDescription = "price")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreen,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = EmeraldGreen
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Phone
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    leadingIcon = {
                        Icon(painter = painterResource(R.drawable.phone), contentDescription = "phone")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldGreen,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = EmeraldGreen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Image Picker
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color(0xFFEAEAEA), shape = RoundedCornerShape(12.dp))
                        .clickable { imagePicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(R.drawable.image),
                                contentDescription = "Pick Image",
                                tint = EmeraldGreen
                            )
                            Text("Tap to pick image", color = EmeraldGreen)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Submit
                Button(
                    onClick = {
                        val priceValue = price.toDoubleOrNull()
                        if (name.isBlank() || phone.isBlank() || priceValue == null || imageUri == null) {
                            Log.e("AddProduct", "Invalid input")
                            return@Button
                        }

                        viewModel.addProduct(
                            name = name,
                            price = priceValue,
                            phone = phone,
                            imageUri = imageUri.toString()
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmeraldGreen,
                        contentColor = Color.White
                    )
                ) {
                    Text("Add Product", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = EmeraldGreen,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Product List") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_PRODUCT) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Product") },
            label = { Text("Add") }
        )
    }
}
