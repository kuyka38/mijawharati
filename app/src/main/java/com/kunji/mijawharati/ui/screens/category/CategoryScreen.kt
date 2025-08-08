package com.kunji.mijawharati.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = EmeraldGreen) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
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
                            Box {
                                IconButton(onClick = { showMenu = !showMenu }) {
                                    Icon(Icons.Default.Menu, contentDescription = "menu", tint = CreamWhite)
                                }

                                DropdownMenu(
                                    expanded = showMenu,
                                    onDismissRequest = { showMenu = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("About") },
                                        onClick = {
                                            showMenu = false
                                            navController.navigate("AboutScreen")
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Contact") },
                                        onClick = {
                                            showMenu = false
                                            navController.navigate("ContactsScreen")
                                        }
                                    )
                                }
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* TODO */ }) {
                                Icon(Icons.Default.Share, contentDescription = "share", tint = CreamWhite)
                            }
                            IconButton(onClick = { /* TODO */ }) {
                                Icon(Icons.Default.Info, contentDescription = "info", tint = CreamWhite)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Welcome to MiJawharati!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(start = 20.dp),
                        color = CreamWhite
                    )

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

                    // Ladies Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 20.dp)
                            .clickable {
                                navController.navigate("LadiesScreen")
                            },
                        elevation = CardDefaults.outlinedCardElevation(5.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = R.drawable.woman),
                                contentDescription = "Womens' Collection",
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
                                text = "Womens' Collection",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Serif,
                                color = CreamWhite,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Men Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 20.dp)
                            .clickable {
                                navController.navigate("MenScreen")
                            },
                        elevation = CardDefaults.outlinedCardElevation(5.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = R.drawable.man),
                                contentDescription = "Men's Collection",
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
                                text = "Men's Collection",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Serif,
                                color = CreamWhite,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                // TODO: navController.navigate("all_products")
                            },
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

@Preview
@Composable
fun CategoryScreenPreview() {
    CategoryScreen(navController = rememberNavController())
}
