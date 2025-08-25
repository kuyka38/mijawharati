package com.kunji.mijawharati.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = EmeraldGreen) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_LANDING) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = CreamWhite) },
                    label = { Text("Home", color = CreamWhite) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_FAVORITES) },
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
                    selected = true,
                    onClick = { /* Already in Profile */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = CreamWhite) },
                    label = { Text("Profile", color = CreamWhite) }
                )
            }
        },
        containerColor = CreamWhite
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Gradient header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(EmeraldGreen, EmeraldGreen.copy(alpha = 0.7f))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = Color.White
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("👤", fontSize = 48.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Profile info card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Kuyka Kunji", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = EmeraldGreen)
                    Text("kunjikuyka@gmail.com", fontSize = 16.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Settings options
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileOption(icon = Icons.Default.Edit, title = "Edit Profile") { /* Handle edit */ }
                ProfileOption(icon = Icons.Default.Warning, title = "Logout", isDestructive = true) { /* Handle logout */ }
            }
        }
    }
}

@Composable
fun ProfileOption(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, isDestructive: Boolean = false, onClick: () -> Unit) {
    val textColor = if (isDestructive) Color.Red else EmeraldGreen
    val iconTint = if (isDestructive) Color.Red else EmeraldGreen

    ListItem(
        headlineContent = {
            Text(title, color = textColor, fontWeight = FontWeight.SemiBold)
        },
        leadingContent = {
            Icon(icon, contentDescription = title, tint = iconTint)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(vertical = 4.dp),
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        supportingContent = null,
        overlineContent = null,
        trailingContent = {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Go", tint = Color.Gray)
        }
    )
}