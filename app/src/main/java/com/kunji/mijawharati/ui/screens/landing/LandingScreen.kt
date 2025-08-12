package com.kunji.mijawharati.ui.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R

val EmeraldGreen = com.kunji.mijawharati.ui.theme.EmeraldGreen
val CreamWhite = com.kunji.mijawharati.ui.theme.CreamWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavController) {
    Scaffold(
        containerColor = CreamWhite,
        topBar = {
            TopAppBar(
                title = {
                    Text("Welcome to MiJawharati")
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Menu */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")

                    }


                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = CreamWhite,
                    navigationIconContentColor = CreamWhite
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
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Person, contentDescription = "Favorite", tint = CreamWhite)
                        Text("profile", color = CreamWhite, fontSize = 12.sp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorite", tint = CreamWhite)
                        Text("favorite", color = CreamWhite, fontSize = 12.sp)
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
            // Banner Image
            Image(
                painter = painterResource(id = R.drawable.img_6),
                contentDescription = "Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(10.dp) )

            // Discover Title
            Text(
                text = "Shop Our unique pieces",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            // Circle icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chain1),
                    contentDescription = "Necklace",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.brace4),
                    contentDescription = "Earrings",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.menchain1),
                    contentDescription = "Chain Pendant",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(id = R.drawable.brace1),
                    contentDescription = "Bangles",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(10.dp) )

            // Section Title
            Text(
                text = "Our Upcoming Korean Collection",
                style = MaterialTheme.typography.titleMedium,

                modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 8.dp)
            )

            // Horizontally scrollable product images
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_3),
                    contentDescription = "Korean Necklace",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(280.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_4),
                    contentDescription = "Korean Earring",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(280.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Korean Pendant",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(280.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            Spacer(modifier = Modifier.height(5.dp) )

            Text(
                text = "Delicate, timeless, and effortlessly chic — our upcoming Korean Collection is your next jewellery obsession.",
                style = MaterialTheme.typography.titleMedium,

                modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 8.dp)
            )


            Spacer(modifier = Modifier.height(15.dp) )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp), // Set your desired height
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_5),
                    contentDescription = "Centered Image",
                    modifier = Modifier.size(450.dp) // Adjust size as needed
                )
            }

            Spacer(modifier = Modifier.height(10.dp) )


            Text(
                text = "Shop our exclusive Men's and Women's collections, where elegance meets craftsmanship. Discover timeless pieces designed to complement every style.",
                fontSize = 15.sp,
                textAlign = TextAlign.Center)



            Button(
                onClick = {
                    navController.navigate("CategoryScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EmeraldGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Shop Now")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen(navController = rememberNavController())
}
