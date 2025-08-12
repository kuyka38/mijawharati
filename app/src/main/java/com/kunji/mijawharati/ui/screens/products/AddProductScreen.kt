package com.kunji.mijawharati.ui.screens.products

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_ADD_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.mijawharati.viewmodel.ProductViewModel

// Theme Colors
private val EmeraldGreen = Color(0xFF006A4E)
private val CreamWhite = Color(0xFFFCFCF7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, viewModel: ProductViewModel) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Log.d("ImagePicker", "Selected image URI: $it")
        }
    }

    Scaffold(
        containerColor = CreamWhite,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Upload a Product",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = CreamWhite),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back", tint = EmeraldGreen)
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = EmeraldGreen)
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
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Product Name",
                    iconRes = R.drawable.name
                )

                Spacer(modifier = Modifier.height(12.dp))

                SimpleTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = "Product Price",
                    iconRes = R.drawable.price
                )

                Spacer(modifier = Modifier.height(12.dp))

                SimpleTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Phone Number",
                    iconRes = R.drawable.phone
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Simple Image Picker
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = EmeraldGreen.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
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
                                tint = EmeraldGreen.copy(alpha = 0.8f),
                                modifier = Modifier.size(50.dp)
                            )
                            Text("Tap to pick image", color = EmeraldGreen, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val priceValue = price.toDoubleOrNull()
                        if (priceValue != null) {
                            imageUri?.toString()?.let { viewModel.addProduct(name, priceValue, phone, it) }
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                ) {
                    Text("Add Product", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun SimpleTextField(value: String, onValueChange: (String) -> Unit, label: String, iconRes: Int) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = EmeraldGreen) },
        leadingIcon = { Icon(painter = painterResource(iconRes), contentDescription = label, tint = EmeraldGreen) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = EmeraldGreen,
            unfocusedBorderColor = EmeraldGreen.copy(alpha = 0.4f),
            focusedLabelColor = EmeraldGreen,
            unfocusedLabelColor = EmeraldGreen
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
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
