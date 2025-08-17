package com.kunji.mijawharati.ui.screens.items

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import kotlinx.coroutines.launch

data class EarringProduct(
    val id: Int,
    val name: String,
    val brand: String,
    val price: String,
    val rating: Double,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarringsScreen(navController: NavController) {
    val mContext = LocalContext.current
    val searchQuery = remember { mutableStateOf("") }
    var selectedBottomItem by remember { mutableStateOf(0) }
    var cartCount by remember { mutableStateOf(0) }

    // State for bottom sheet
    var selectedProduct by remember { mutableStateOf<EarringProduct?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    val earringProducts = listOf(
        EarringProduct(1, "Diamond Earring", "Ocean Elegance", "KES 200", 4.7, R.drawable.earing1),
        EarringProduct(2, "Butterfly-Crystal Earring", "Golden Touch", "KES 400", 4.6, R.drawable.earing2),
        EarringProduct(3, "Gold-Flower Earring", "House of bay", "KES 500", 4.9, R.drawable.earing3),
        EarringProduct(4, "Chanel Earrings", "CoCo Chanel", "KES 400", 4.5, R.drawable.earing4),
        EarringProduct(5, "Men Silver Studs", "Shein", "KES 900", 4.5, R.drawable.earing5),
        EarringProduct(6, "Men Chrome Studs", "Chrome Hearts", "KES 800", 4.5, R.drawable.earing6),
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = selectedBottomItem == 0,
                    onClick = { selectedBottomItem = 0 },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedBottomItem == 1,
                    onClick = {
                        selectedBottomItem = 1
                        navController.navigate(ROUT_CART)
                    },
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart") },
                    label = { Text("Cart") }
                )
                NavigationBarItem(
                    selected = selectedBottomItem == 2,
                    onClick = { selectedBottomItem = 2 },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(EmeraldGreen)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate(ROUT_LANDING) {
                        popUpTo("LandingScreen") { inclusive = true }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Earrings Collection",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                BadgedBox(
                    badge = {
                        if (cartCount > 0) {
                            Badge { Text(cartCount.toString()) }
                        }
                    }
                ) {
                    IconButton(onClick = { navController.navigate("LandingScreen") }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.White)
                    }
                }
            }

            // Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.earingback),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = "From elegant studs to bold statement earrings — discover styles that complete every look.",
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                )
            }

            // Search
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = { Text("Search earrings...", fontSize = 14.sp) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = EmeraldGreen,
                    unfocusedBorderColor = Color.Gray
                )
            )

            // Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.heightIn(max = 1000.dp)
            ) {
                items(earringProducts) { product ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                selectedProduct = product
                                coroutineScope.launch { sheetState.show() }
                            },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = product.imageRes),
                                contentDescription = product.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(120.dp)
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(product.brand, fontSize = 12.sp, color = Color.Gray)
                            Text(product.price, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = EmeraldGreen)
                            Text("⭐ ${product.rating}", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }

        // Product Details Bottom Sheet
        if (selectedProduct != null) {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch { sheetState.hide() }
                        .invokeOnCompletion { if (!sheetState.isVisible) selectedProduct = null }
                },
                sheetState = sheetState,
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.9f) // Taller sheet (90% of screen height)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    selectedProduct?.let { product ->
                        Image(
                            painter = painterResource(id = product.imageRes),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(product.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        Text(product.brand, fontSize = 16.sp, color = Color.Gray)
                        Text(product.price, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = EmeraldGreen)
                        Text("⭐ ${product.rating}", fontSize = 14.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                cartCount++
                                Toast.makeText(mContext, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Add to Cart", color = Color.White, fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Buy Now", color = Color.White, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EarringsScreenPreview() {
    EarringsScreen(navController = rememberNavController())
}
