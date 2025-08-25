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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.model.Product
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_SCREEN_LIST
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, viewModel: ProductViewModel) {
    val cartProducts by viewModel.cartProducts.observeAsState(emptyList())

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
        containerColor = CreamWhite
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
                    CartProductItem(
                        product = product,
                        onRemoveClick = { viewModel.removeFromCart(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun CartProductItem(product: Product, onRemoveClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* navigate to details if needed */ },
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
                Text(text = product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EmeraldGreen)
                Text(text = "Ksh${product.price}", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Seller: ${product.phone}", fontSize = 12.sp, color = Color.DarkGray)
            }

            // Remove from Cart
            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove from cart",
                    tint = Color.Red
                )
            }
        }
    }
}


