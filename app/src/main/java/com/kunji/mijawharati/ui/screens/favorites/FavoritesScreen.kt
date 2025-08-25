package com.kunji.mijawharati.ui.screens.products

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.navigation.ROUT_PROFILE
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_SCREEN_LIST
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: ProductViewModel
) {
    val favorites by viewModel.favoriteProducts.observeAsState(emptyList())

    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()

    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            selectedProduct?.let { product ->
                BottomSheetContent(product = product, onRemoveClick = {
                    viewModel.toggleFavorite(product)
                    coroutineScope.launch { sheetState.hide() }
                })
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("My Favorites", color = CreamWhite, fontSize = 20.sp) },
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
            bottomBar = {
                NavigationBar(containerColor = EmeraldGreen) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(ROUT_LANDING) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = CreamWhite) },
                        label = { Text("Home", color = CreamWhite) }
                    )
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* Already on Favorites */ },
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
            },
            containerColor = CreamWhite
        ) { paddingValues ->
            if (favorites.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No favorite products yet!", color = Color.Gray, fontSize = 16.sp)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favorites) { product ->
                        FavoriteProductItem(
                            product = product,
                            onRemoveClick = { viewModel.toggleFavorite(product) },
                            onClick = {
                                selectedProduct = product
                                coroutineScope.launch { sheetState.show() }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteProductItem(
    product: Product,
    onRemoveClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Product Image
                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(product.imagePath)),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Product Info
                Text(
                    text = product.name,
                    fontSize = 15.sp,
                    color = EmeraldGreen,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = "Ksh${product.price}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }

            // Floating Remove Button (Top Right over Image)
            IconButton(
                onClick = onRemoveClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .size(28.dp)
                    .background(Color.White.copy(alpha = 0.7f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove",
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    product: Product,
    onRemoveClick: () -> Unit
) {
    // Dynamically get screen height and take 95%
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val sheetHeight = screenHeight * 0.90f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(sheetHeight)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Product Image
        Image(
            painter = rememberAsyncImagePainter(model = Uri.parse(product.imagePath)),
            contentDescription = "Product Image",
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Info
        Text(
            text = product.name,
            fontSize = 20.sp,
            color = EmeraldGreen,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ksh${product.price}",
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Remove Button
        Button(
            onClick = onRemoveClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Remove from Favorites", color = Color.White)
        }
    }
}
