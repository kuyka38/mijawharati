package com.kunji.mijawharati.ui.screens.products

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.viewmodel.ProductViewModel
import com.kunji.mijawharati.viewmodel.CartViewModel
import com.kunji.mijawharati.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.collectAsState

// Overlay for image gradient
private val SoftOverlay = androidx.compose.ui.graphics.Color(0x88000000)

class FavoritesViewModel : ViewModel() {
    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    val favorites: StateFlow<List<Product>> = _favorites

    fun toggleFavorite(product: Product) {
        val current = _favorites.value.toMutableList()
        if (current.any { it.id == product.id }) {
            current.removeAll { it.id == product.id }
        } else {
            current.add(product)
        }
        _favorites.value = current
    }

    fun isFavorite(product: Product): Boolean {
        return _favorites.value.any { it.id == product.id }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreenList(
    navController: NavController,
    viewModel: ProductViewModel,
    favoritesViewModel: FavoritesViewModel,
    cartViewModel: CartViewModel = viewModel()
) {
    val productList by viewModel.allProducts.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts = productList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val cartItems = cartViewModel.cartItems   // directly observable because it's a SnapshotStateList
    val cartCount = cartItems.sumOf { it.quantity }



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
                        IconButton(onClick = { navController.navigate(ROUT_LANDING) }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = EmeraldGreen
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = CreamWhite
                    )
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
                        cursorColor = EmeraldGreen
                    ),
                    shape = RoundedCornerShape(14.dp)
                )
            }
        },
        bottomBar = {
            NavigationBar(containerColor = EmeraldGreen) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_LANDING) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Dashboard",
                            tint = CreamWhite
                        )
                    },
                    label = { Text("Home", color = CreamWhite, fontSize = 12.sp) }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_CART) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (cartCount > 0) {
                                    Badge { Text(cartCount.toString()) }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = CreamWhite
                            )
                        }
                    },
                    label = { Text("Cart", color = CreamWhite, fontSize = 12.sp) }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_FAVORITES) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorites",
                            tint = CreamWhite
                        )
                    },
                    label = { Text("Favorites", color = CreamWhite, fontSize = 12.sp) }
                )
            }
        },
        containerColor = CreamWhite
    ) { paddingValues ->
        if (filteredProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
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
                contentPadding = PaddingValues(
                    start = 12.dp,
                    end = 12.dp,
                    bottom = paddingValues.calculateBottomPadding() + 12.dp,
                    top = 12.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(filteredProducts) { product ->
                    ProductItem(navController, product, viewModel, favoritesViewModel, cartViewModel) {
                        selectedProduct = product
                    }
                }
            }
        }
    }

    if (selectedProduct != null) {
        ModalBottomSheet(
            onDismissRequest = { selectedProduct = null },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(0.9f),
            containerColor = CreamWhite,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            ProductDetailsContent(
                product = selectedProduct!!,
                favoritesViewModel = favoritesViewModel,
                cartViewModel = cartViewModel
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductItem(
    navController: NavController,
    product: Product,
    viewModel: ProductViewModel,
    favoritesViewModel: FavoritesViewModel,
    cartViewModel: CartViewModel,
    onClick: () -> Unit
) {
    val painter: Painter = rememberAsyncImagePainter(model = product.imagePath?.let { Uri.parse(it) })
    val favorites by favoritesViewModel.favorites.collectAsState()
    val isFavorite = favorites.any { it.id == product.id }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CreamWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
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
                                colors = listOf(androidx.compose.ui.graphics.Color.Transparent, SoftOverlay)
                            )
                        )
                )

                IconButton(
                    onClick = { favoritesViewModel.toggleFavorite(product) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(36.dp)
                        .background(
                            CreamWhite.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(50)
                        )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) EmeraldGreen else EmeraldGreen.copy(alpha = 0.6f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .background(CreamWhite)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(product.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = EmeraldGreen)
                Spacer(modifier = Modifier.height(2.dp))
                Text("Ksh ${product.price}", fontSize = 14.sp, color = EmeraldGreen.copy(alpha = 0.85f))

                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {
                        val cartItem = CartItem(
                            id = product.id.toString(),   // ✅ FIX
                            name = product.name,
                            price = product.price,
                            imagePath = product.imagePath,
                            quantity = 1
                        )
                        cartViewModel.addItem(cartItem)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Add to Cart", tint = CreamWhite)
                    Spacer(Modifier.width(6.dp))
                    Text("Add to Cart", color = CreamWhite)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductDetailsContent(
    product: Product,
    favoritesViewModel: FavoritesViewModel,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current
    val imagePainter: Painter = rememberAsyncImagePainter(model = product.imagePath?.let { Uri.parse(it) })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EmeraldGreen, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Ksh ${product.price}", fontSize = 18.sp, color = EmeraldGreen.copy(alpha = 0.9f), modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            IconButton(onClick = { generateProductPDF(context, product) }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "PDF",
                    tint = EmeraldGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                val smsIntent = Intent(Intent.ACTION_SENDTO)
                smsIntent.data = "smsto:${product.phone}".toUri()
                smsIntent.putExtra("sms_body", "Hello Seller,...?")
                context.startActivity(smsIntent)
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldGreen),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Send, contentDescription = "Message", tint = EmeraldGreen)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Message Seller", color = EmeraldGreen)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val cartItem = CartItem(
                    id = product.id.toString(),   // ✅ FIX
                    name = product.name,
                    price = product.price,
                    imagePath = product.imagePath,
                    quantity = 1
                )
                cartViewModel.addItem(cartItem)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Add to Cart", tint = CreamWhite)
            Spacer(Modifier.width(6.dp))
            Text("Add to Cart", color = CreamWhite)
        }
    }
}
