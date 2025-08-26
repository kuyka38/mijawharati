package com.kunji.mijawharati.ui.screens.products

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_SCREEN_LIST
import com.kunji.mijawharati.navigation.ROUT_PROFILE
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, viewModel: ProductViewModel) {
    val context = LocalContext.current
    val cartProducts by viewModel.cartProducts.observeAsState(emptyList())

    // Quantities map
    var quantities by remember { mutableStateOf(mutableMapOf<Int, Int>()) }

    // Calculate total dynamically
    val totalPrice: Double = cartProducts.sumOf { product ->
        val qty = quantities[product.id] ?: 1
        product.price * qty
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart", fontSize = 20.sp, color = CreamWhite) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUT_PRODUCT_SCREEN_LIST) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = CreamWhite
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = EmeraldGreen)
            )
        },
        containerColor = CreamWhite,
        bottomBar = {
            Column {
                if (cartProducts.isNotEmpty()) {
                    Column(modifier = Modifier.background(Color.White)) {
                        // Total Price Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Total (VAT included):", fontSize = 14.sp, color = Color.Gray)
                                Text(
                                    text = "Ksh${"%.2f".format(totalPrice)}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldGreen
                                )
                            }
                        }

                        // Message Seller and Pay Now Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    if (cartProducts.isNotEmpty()) {
                                        val sellerPhone = cartProducts.first().phone
                                        val smsIntent = Intent(Intent.ACTION_SENDTO)
                                        smsIntent.data = "smsto:$sellerPhone".toUri()
                                        smsIntent.putExtra("sms_body", "Hello this is my address [  ],I want to buy [  ] and This are the delivery instructions [  ].")
                                        context.startActivity(smsIntent)
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = EmeraldGreen
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Message Seller", color = EmeraldGreen, fontSize = 14.sp)
                            }

                            Button(
                                onClick = {
                                    val simToolKitLaunchIntent =
                                        context.packageManager.getLaunchIntentForPackage("com.android.stk")
                                    simToolKitLaunchIntent?.let { context.startActivity(it) }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Pay Now", color = CreamWhite, fontSize = 14.sp)
                            }
                        }
                    }
                }

                // Bottom Navigation
                NavigationBar(containerColor = EmeraldGreen) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(ROUT_LANDING) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CreamWhite,
                            unselectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White,
                            indicatorColor = EmeraldGreen
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(ROUT_FAVORITES) },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                        label = { Text("Favorites") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CreamWhite,
                            unselectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White,
                            indicatorColor = EmeraldGreen
                        )
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* stay on cart */ },
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
                        label = { Text("Cart") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CreamWhite,
                            unselectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White,
                            indicatorColor = EmeraldGreen
                        )
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(ROUT_PROFILE) },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = CreamWhite,
                            unselectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White,
                            indicatorColor = EmeraldGreen
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        if (cartProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Your cart is empty", color = Color.Gray, fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cartProducts) { product ->
                    val qty = quantities[product.id] ?: 1
                    CartProductItem(
                        product = product,
                        quantity = qty,
                        onQuantityChange = { newQty ->
                            quantities = quantities.toMutableMap().apply {
                                this[product.id] = newQty
                            }
                        },
                        onRemoveClick = { viewModel.removeFromCart(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun CartProductItem(
    product: Product,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { /* navigate to details if needed */ },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image
            Image(
                painter = rememberAsyncImagePainter(model = Uri.parse(product.imagePath)),
                contentDescription = "Product Image",
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            // Product Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = product.name,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreen,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onRemoveClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove from cart",
                            tint = EmeraldGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Price (quantity applied)
                Text(
                    text = "Ksh${"%.2f".format(product.price * quantity)}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                // Quantity controls
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(top = 6.dp)
                ) {
                    OutlinedButton(
                        onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("-", fontSize = 18.sp)
                    }

                    Text(
                        text = quantity.toString(),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    OutlinedButton(
                        onClick = { onQuantityChange(quantity + 1) },
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("+", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
