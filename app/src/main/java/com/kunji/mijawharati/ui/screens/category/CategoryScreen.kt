package com.kunji.mijawharati.ui.screens.category

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {
    val mContext = LocalContext.current
    var selectedIndex by remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onMenuItemClick = { route ->
                    scope.launch { drawerState.close() }
                    when (route) {
                        "AboutScreen" -> navController.navigate("AboutScreen")
                        "ContactsScreen" -> navController.navigate("ContactsScreen")
                    }
                }
            )
        }
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar(containerColor = EmeraldGreen) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home",
                                tint = CreamWhite
                            )
                        },
                        label = { Text("Home") },
                        selected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 }
                    )


                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = CreamWhite
                            )
                        },
                        label = { Text("Cart") },
                        selected = selectedIndex == 2,
                        onClick = {
                            selectedIndex = 2
                            navController.navigate(ROUT_CART)
                        }
                    )


                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* Add action */ },
                    containerColor = Color.LightGray
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.elegant),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .background(Color.Transparent)
                    ) {
                        TopAppBar(
                            title = { Text("Categories") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = EmeraldGreen,
                                titleContentColor = CreamWhite
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(Icons.Default.Menu, contentDescription = "menu", tint = CreamWhite)
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    val shareIntent = Intent(Intent.ACTION_SEND)
                                    shareIntent.type = "text/plain"
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
                                    mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
                                }) {
                                    Icon(Icons.Default.Share, contentDescription = "share", tint = CreamWhite)
                                }

                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))



                        Text(
                            text = "Discover our unique Jewelleries across the different categories. Find something for every vibe.",
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 4.dp),
                            color = CreamWhite
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Shop by Category",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(start = 20.dp),
                            color = CreamWhite
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        CategoryCard(
                            imageRes = R.drawable.img_8,
                            title = "Womens' Collection",
                            onClick = { navController.navigate("LadiesScreen") }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        CategoryCard(
                            imageRes = R.drawable.img_11,
                            title = "Men's Collection",
                            onClick = { navController.navigate("MenScreen") }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = { /* TODO */ },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                            ) {
                                Text(text = "Explore All Products", color = CreamWhite)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Follow us on Instagram @mijawharati_ke",
                                fontSize = 14.sp,
                                color = CreamWhite,
                                fontFamily = FontFamily.Serif,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "Need help? Contact us at support@mijawharati.co.ke",
                                fontSize = 14.sp,
                                color = CreamWhite,
                                fontFamily = FontFamily.Serif,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun DrawerContent(onMenuItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "MIJAWHARATI",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = EmeraldGreen,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        LazyColumn {


            item {
                Text(
                    "Contact us",
                    fontSize = 18.sp,
                    color = EmeraldGreen,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .clickable { onMenuItemClick("ContactsScreen") }
                )
            }
            item {
                Text(
                    "About us",
                    fontSize = 18.sp,
                    color = EmeraldGreen,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .clickable { onMenuItemClick("AboutScreen") }
                )
            }



        }

        Spacer(modifier = Modifier.height(50.dp))

        Text("What we offer", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = EmeraldGreen,)
        Spacer(modifier = Modifier.height(25.dp))
        Text("• Delivery within 5 days", fontSize = 15.sp, color = Color.Gray,)
        Spacer(modifier = Modifier.height(20.dp))

        Text("• Free standard delivery", fontSize = 15.sp, color = Color.Gray,)
        Spacer(modifier = Modifier.height(20.dp))
        Text("• Free Returns", fontSize = 15.sp, color = Color.Gray,)
        Spacer(modifier = Modifier.height(20.dp))

        Spacer(modifier = Modifier.weight(1f))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(Icons.Default.Settings, contentDescription = null)

        }
    }
}

@Composable
fun CategoryCard(imageRes: Int, title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 20.dp)
            .clickable { onClick() },
        elevation = CardDefaults.outlinedCardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "favorite",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                tint = CreamWhite
            )
            Text(
                text = title,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CategoryScreenPreview() {
    CategoryScreen(navController = rememberNavController())
}
