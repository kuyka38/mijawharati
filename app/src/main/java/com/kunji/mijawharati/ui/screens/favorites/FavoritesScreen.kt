package com.kunji.mijawharati.ui.screens.products

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: ProductViewModel
) {
    val favorites by viewModel.favoriteProducts.observeAsState(emptyList())
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Favorites", color = CreamWhite, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favorites) { product ->
                    FavoriteProductItem(
                        product = product,
                        onRemoveClick = { viewModel.toggleFavorite(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteProductItem(
    product: Product,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Could navigate to details */ },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image
            Image(
                painter = rememberAsyncImagePainter(model = Uri.parse(product.imagePath)),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Product Info
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, fontSize = 16.sp, color = EmeraldGreen)
                Text(text = "Ksh${product.price}", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Seller: ${product.phone}", fontSize = 12.sp, color = Color.DarkGray)
            }

            // Remove from Favorites
            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove",
                    tint = Color.Red
                )
            }
        }
    }
}
