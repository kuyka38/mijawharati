package com.kunji.mijawharati.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.navigation.ROUT_PROFILE
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen

// --------- MODEL ---------
data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val quantity: Int = 1
)

// --------- VIEWMODEL ---------
class CartViewModel : androidx.lifecycle.ViewModel() {
    private val _cartItems = androidx.compose.runtime.mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addItem(item: CartItem) {
        val existing = _cartItems.find { it.id == item.id }
        if (existing != null) {
            val index = _cartItems.indexOf(existing)
            _cartItems[index] = existing.copy(quantity = existing.quantity + 1)
        } else {
            _cartItems.add(item)
        }
    }

    fun removeItem(itemId: String) {
        _cartItems.removeAll { it.id == itemId }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotal(): Double {
        return _cartItems.sumOf { it.price * it.quantity }
    }
}

// --------- SCREEN ---------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel = viewModel()) {
    val cartItems by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(cartViewModel.cartItems) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(" Shopping Cart") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUT_LANDING) {
                            popUpTo("LandingScreen") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to Categories",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = CreamWhite,
        bottomBar = {
            NavigationBar(
                containerColor = EmeraldGreen
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_LANDING) {
                            popUpTo(ROUT_LANDING) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = CreamWhite
                        )
                    },
                    label = { Text("Home", color = CreamWhite) }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_FAVORITES) {
                            popUpTo(ROUT_FAVORITES) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorites",
                            tint = CreamWhite
                        )
                    },
                    label = { Text("Favorites", color = CreamWhite) }
                )







                NavigationBarItem(
                    selected = true, // current screen
                    onClick = { /* Already in Cart */ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Cart",
                            tint = CreamWhite
                        )
                    },
                    label = { Text("Cart", color = CreamWhite) }
                )




                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_PROFILE) {
                            popUpTo(ROUT_PROFILE) { inclusive = true }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile",
                            tint = CreamWhite
                        )
                    },
                    label = { Text("Profile", color = CreamWhite) }
                )















            }
        }
    ) { padding ->
        if (cartViewModel.cartItems.isEmpty()) {
            // ---------- EMPTY STATE ----------
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    modifier = Modifier.size(100.dp),
                    tonalElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.shoppingcart),
                            contentDescription = "Cart",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Your cart is empty!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldGreen
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Browse categories and add items to your cart.",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        } else {
            // ---------- CART LIST ----------
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartViewModel.cartItems) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(item.name, fontWeight = FontWeight.Bold)
                            Text("x${item.quantity}")
                            Text("Ksh ${item.price * item.quantity}")
                            IconButton(onClick = { cartViewModel.removeItem(item.id) }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Remove",
                                    tint = EmeraldGreen
                                )
                            }
                        }
                    }
                }

                Divider()

                // ---------- TOTAL ----------
                Text(
                    text = "Total: Ksh ${cartViewModel.getTotal()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Button(
                    onClick = { /* Checkout logic */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                ) {
                    Text("Proceed to Checkout", color = CreamWhite)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController())
}
