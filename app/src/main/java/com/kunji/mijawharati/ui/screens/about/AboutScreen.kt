package com.kunji.mijawharati.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    val background = painterResource(id = R.drawable.img_10) // Your background image

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About Us",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUT_LANDING) {
                            popUpTo("LandingScreen") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Back to Landing",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(painter = background, contentScale = ContentScale.Crop)
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(color = Color(0xFFFCFCF4).copy(alpha = 0.85f))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.hibiscus), // your app logo
                    contentDescription = "Logo",
                    tint = EmeraldGreen,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "MiJawharati",
                    fontSize = 32.sp,
                    color = Color(0xFF046A38), // Emerald green
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "MiJawharati is a jewellery brand inspired by timeless elegance and authentic craftsmanship. We celebrate beauty, heritage, and self-expression through our carefully curated collections.",
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = "Our Mission",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF046A38)
                )
                Text(
                    text = "To empower individuals with jewellery that tells their story — bold, beautiful, and uniquely theirs.",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(44.dp))

                Text(
                    text = "A Note From The Founder",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF046A38)
                )
                Text(
                    text = "MiJawharati was born from a deep love for beauty that speaks — pieces that hold meaning, memory, and strength.\n" +
                            "\n" +
                            "– Kuyuka",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = "Follow Us",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF046A38)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Instagram: @mijawharati.ke",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Facebook: MiJawharati",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = "Tiktok: Mi-Jawharati",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navController = rememberNavController())
}
