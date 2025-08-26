package com.kunji.mijawharati.ui.screens.landing

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_ANKLETS
import com.kunji.mijawharati.navigation.ROUT_BRACELETS
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_EARRINGS
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_NECKLACES
import com.kunji.mijawharati.navigation.ROUT_PRIVACY
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_SCREEN_LIST
import com.kunji.mijawharati.navigation.ROUT_PROFILE
import com.kunji.mijawharati.navigation.ROUT_RINGS
import com.kunji.mijawharati.navigation.ROUT_UPLOAD_CONTACT
import com.kunji.mijawharati.navigation.ROUT_WATCHES
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Data class for grid products
data class Product(
    val name: String,
    val imageRes: Int
)

@Composable
fun ProductGrid(navController: NavController) {
    val products = listOf(
        Product("Necklaces", R.drawable.img_19),
        Product("Bracelets", R.drawable.img_18),
        Product("Rings", R.drawable.img_20),
        Product("Earings", R.drawable.earing),
        Product("Watches", R.drawable.img_22),
        Product("Anklets", R.drawable.ankletback)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        when (product.name) {
                            "Necklaces" -> navController.navigate(ROUT_NECKLACES)
                            "Bracelets" -> navController.navigate(ROUT_BRACELETS)
                            "Rings" -> navController.navigate(ROUT_RINGS)
                            "Earings" -> navController.navigate(ROUT_EARRINGS)
                            "Watches" -> navController.navigate(ROUT_WATCHES)
                            "Anklets" -> navController.navigate(ROUT_ANKLETS)
                        }
                    }
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mContext = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp),
                drawerContainerColor = CreamWhite
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "MIJAWHARATI",
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Contact us",
                        fontSize = 18.sp,
                        color = EmeraldGreen,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable {
                                scope.launch { drawerState.close() }
                                navController.navigate(ROUT_UPLOAD_CONTACT)
                            }
                    )

                    Spacer(modifier = Modifier.height(17.dp))

                    Text(
                        text = "About us",
                        fontSize = 18.sp,
                        color = EmeraldGreen,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable {
                                scope.launch { drawerState.close() }
                                navController.navigate("AboutScreen")
                            }
                    )

                    Spacer(modifier = Modifier.height(17.dp))


                    Text(
                        text = "Privacy Policy",
                        fontSize = 18.sp,
                        color = EmeraldGreen,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable {
                                scope.launch { drawerState.close() }
                                navController.navigate(ROUT_PRIVACY)
                            }
                    )

                    Spacer(modifier = Modifier.height(45.dp))

                    Text(
                        text = "What we offer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = EmeraldGreen
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("• Delivery within 5 days", fontSize = 15.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("• Free standard delivery", fontSize = 15.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("• Free Returns", fontSize = 15.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    ) {
        Scaffold(
            containerColor = CreamWhite,
            topBar = {
                TopAppBar(
                    title = { Text("Welcome to MiJawharati") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Check out MiJawharati – discover elegant jewelry collections!"
                                )
                            }
                            mContext.startActivity(
                                Intent.createChooser(shareIntent, "Share via")
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = CreamWhite
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = EmeraldGreen,
                        titleContentColor = CreamWhite,
                        navigationIconContentColor = CreamWhite,
                        actionIconContentColor = CreamWhite
                    )
                )
            },

            bottomBar = {
                BottomAppBar(containerColor = EmeraldGreen) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Home, contentDescription = "Home", tint = CreamWhite)
                            Text("Home", color = CreamWhite, fontSize = 12.sp)
                        }



                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                navController.navigate(ROUT_FAVORITES)
                            }
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Favorite",
                                tint = CreamWhite
                            )
                            Text("Favorites", color = CreamWhite, fontSize = 12.sp)
                        }





                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                navController.navigate(ROUT_CART)
                            }
                        ) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = CreamWhite
                            )
                            Text("Cart", color = CreamWhite, fontSize = 12.sp)
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                navController.navigate(ROUT_PROFILE)
                            }
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = CreamWhite
                            )
                            Text("Profile", color = CreamWhite, fontSize = 12.sp)
                        }





                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(CreamWhite)
            ) {
                // Carousel logic
                val carouselImages = listOf(R.drawable.landing, R.drawable.landing, R.drawable.landing)
                var currentImageIndex by remember { mutableStateOf(0) }

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(1000)
                        currentImageIndex = (currentImageIndex + 1) % carouselImages.size
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.landing),
                    contentDescription = "Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Shop by Categories",
                    fontSize = 23.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                )

                // Insert Grid here
                ProductGrid(navController)

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Unique  Pieces",
                    fontSize = 23.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 15.dp, bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.korean1),
                        contentDescription = "Korean Necklace",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(280.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.korean),
                        contentDescription = "Korean Earring",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(280.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = "Korean Pendant",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(280.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.img_4),
                        contentDescription = "Korean Pendant",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(280.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )



                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Delicate, timeless, and effortlessly chic",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))


                Button(
                    onClick = { navController.navigate(ROUT_PRODUCT_SCREEN_LIST)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("View All Products")
                }

                Spacer(modifier = Modifier.height(35.dp))

                // Auto-slide carousel logic
                val carouselImages = listOf(R.drawable.img, R.drawable.img_2, R.drawable.img_1)
                var currentImageIndex by remember { mutableStateOf(0) }

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(1000) // 1 second
                        currentImageIndex = (currentImageIndex + 1) % carouselImages.size
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Find an everyday statement piece - simple but elegant",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp)
                )


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen(navController = rememberNavController())
}
