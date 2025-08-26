package com.kunji.mijawharati.ui.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_CART
import com.kunji.mijawharati.navigation.ROUT_FAVORITES
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.navigation.ROUT_PRIVACY
import com.kunji.mijawharati.navigation.ROUT_UPLOAD_CONTACT
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    val context = LocalContext.current

    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedImage = it }
    }

    Scaffold(
        containerColor = CreamWhite,
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
                    onClick = { /* Already on Profile */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = CreamWhite) },
                    label = { Text("Profile", color = CreamWhite) }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Top Profile Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(EmeraldGreen, EmeraldGreen.copy(alpha = 0.7f))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .clickable { launcher.launch("image/*") },
                        color = Color.White,
                        shape = CircleShape
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(selectedImage ?: R.drawable.img_9),
                            contentDescription = "Profile Image",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "My Profile",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // List Items (Brackets) with spacing
            ProfileOptionRow(
                icon = Icons.Default.Call,
                text = "Contact Us",
                onClick = { navController.navigate(ROUT_UPLOAD_CONTACT) }
            )

            Spacer(modifier = Modifier.height(15.dp))

            ProfileOptionRow(
                icon = Icons.Default.Info,
                text = "About Us",
                onClick = { navController.navigate("AboutScreen") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            ProfileOptionRow(
                icon = Icons.Default.Favorite,
                text = "Wishlist",
                onClick = { navController.navigate(ROUT_FAVORITES) }
            )

            Spacer(modifier = Modifier.height(15.dp))

            ProfileOptionRow(
                icon = Icons.Default.Phone,
                text = "Call Us",
                onClick = {
                    val callIntent = Intent(Intent.ACTION_DIAL)
                    callIntent.data = "tel:0740644678".toUri()
                    context.startActivity(callIntent)
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            ProfileOptionRow(
                icon = Icons.Default.Warning,
                text = "Privacy Policy",
                onClick = { navController.navigate(ROUT_PRIVACY) }
            )

        }
    }
}

@Composable
fun ProfileOptionRow(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(Color(0xFFF8F6F0))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}
