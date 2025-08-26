package com.kunji.mijawharati.ui.screens.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_ADD_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_EDIT_PRODUCT
import com.kunji.mijawharati.navigation.ROUT_PRODUCT_LIST
import com.kunji.mijawharati.navigation.ROUT_VIEW_CONTACT
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Admin Dashboard",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            // Top Section with Welcome and Image
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hey Admin... ",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 30.sp
                        )
                    )

                    Text(
                        text = "Welcome to Admin Dashboard",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.img_24),
                    contentDescription = "Admin Illustration",
                    modifier = Modifier.size(190.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Suppliers Section
            Text(
                text = "Different Suppliers",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Supplier Cards (Horizontal Scroll)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    FeatureCard(
                        imageRes = R.drawable.img_25,
                        url = "https://www.alibaba.com/",
                        modifier = Modifier.width(170.dp)
                    )
                }
                item {
                    FeatureCard(
                        imageRes = R.drawable.img_26,
                        url = "https://www.aliexpress.com/",
                        modifier = Modifier.width(170.dp)
                    )
                }
                item {
                    FeatureCard(
                        imageRes = R.drawable.img_27,
                        url = "https://www.amazon.com/",
                        modifier = Modifier.width(170.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Admin Functions Section
            Text(
                text = "Admin Functions",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 5.dp)
            )

            TaskCard(
                title = "Add Product",
                subtitle = "Navigate to add a new product",
                color = Color(0xFF0E4807)
            ) {
                navController.navigate(ROUT_ADD_PRODUCT)
            }

            TaskCard(
                title = "Product List",
                subtitle = "View all products available",
                color = Color(0xFFE8E27D)
            ) {
                navController.navigate(ROUT_PRODUCT_LIST)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Messages from users",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )


            TaskCard(
                title = "User Messages",
                subtitle = "Update or modify existing products",
                color = Color(0xFFFF5722)
            ) {
                navController.navigate(ROUT_VIEW_CONTACT)
            }
        }
    }
}

@Composable
fun FeatureCard(
    imageRes: Int,
    url: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = modifier
            .height(150.dp)
            .clickable {
                if (url.isNotEmpty()) {
                    uriHandler.openUri(url)
                }
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TaskCard(title: String, subtitle: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color, shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminDashboardScreenPreview() {
    AdminDashboardScreen(navController = rememberNavController())
}
