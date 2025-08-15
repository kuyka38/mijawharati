package com.kunji.mijawharati.ui.screens.men

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
import androidx.compose.material.icons.filled.Favorite
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
import com.kunji.mijawharati.ui.theme.EmeraldGreen

// Data class for products
data class MenProduct(
    val id: Int,
    val name: String,
    val brand: String,
    val price: String,
    val rating: Double,
    val imageRes: Int
)

@Composable
fun MenScreen(navController: NavController) {
    val mContext = LocalContext.current
    val searchQuery = remember { mutableStateOf("") }
    var selectedBottomItem by remember { mutableStateOf(0) }

    // Cart state
    var cartCount by remember { mutableStateOf(0) }

    val menProducts = listOf(
        MenProduct(1, "Men's Silver Bracelet Set", "Luxury sets", "KES 1,000", 4.8, R.drawable.menbrace1),
        MenProduct(2, "Mens Hoop Earrings", "Jewel Stones", "KES 1,500", 4.6, R.drawable.menear2),
        MenProduct(3, "Cuban Link Chains", "Golden Touch", "KES 2,000", 4.9, R.drawable.menchain1),
        MenProduct(4, "Timex Waterbury Watch", "Timex Watches", "KES 6,000", 4.7, R.drawable.menwatch2),
        MenProduct(5, "Men’s Legacy Watch", "MVMT Watches", "KES 5,000", 4.8, R.drawable.menwatch1),
        MenProduct(6, "Cufflinks Box", "Cuff Daddy", "KES 800", 4.5, R.drawable.mencuff1)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = selectedBottomItem == 0,
                    onClick = { selectedBottomItem = 0 /* Navigate home */ },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedBottomItem == 1,
                    onClick = { selectedBottomItem = 1 /* Navigate favorites */ },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") }
                )
                NavigationBarItem(
                    selected = selectedBottomItem == 2,
                    onClick = { selectedBottomItem = 2 /* Navigate profile */ },
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
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(EmeraldGreen)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("CategoryScreen") {
                        popUpTo("CategoryScreen") { inclusive = true }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back to category",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Mens Collection",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Cart Icon with badge
                BadgedBox(
                    badge = {
                        if (cartCount > 0) {
                            Badge { Text(cartCount.toString()) }
                        }
                    }
                ) {
                    IconButton(onClick = {
                        navController.navigate("ROUT_CART")
                    }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.White)
                    }
                }
            }

            // Banner Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_15),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = "Discover the MiJawharati Men’s Collection — a bold fusion of strength, elegance, and timeless design. From sleek chains to statement rings, each piece is crafted to reflect confidence and refined style",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                )
            }

            // Search bar
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = { Text("Search jewellery...", fontSize = 14.sp) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = EmeraldGreen,
                    unfocusedBorderColor = Color.Gray
                )
            )

            // Products Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.heightIn(max = 1000.dp)
            ) {
                items(menProducts) { product ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { /* Navigate to details */ },
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
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(
                                onClick = {
                                    Toast.makeText(
                                        mContext,
                                        "${product.name} added to cart",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    cartCount++
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Add to Cart", color = Color.White, fontSize = 12.sp)
                            }
                            Button(
                                onClick = {
                                    val simToolKitLaunchIntent =
                                        mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                    simToolKitLaunchIntent?.let { mContext.startActivity(it) }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Buy Now", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenScreenPreview() {
    MenScreen(navController = rememberNavController())
}
