package com.kunji.mijawharati.ui.screens.privacy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.navigation.ROUT_PROFILE
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.Transparent, // Make container transparent to show background image
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Privacy Policy",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUT_PROFILE) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {

            // Background Image
            Image(
                painter = painterResource(id = R.drawable.img_10), // Replace with your desired background
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Scrollable content over background
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Effective Date: August 26, 2025",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val sections = listOf(
                    "1. Introduction" to "Welcome to Mijawharati. We value your privacy and are committed to protecting your personal data. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application.",
                    "2. Information We Collect" to "a. Personal Information:\n- Name\n- Email address\n- Phone number\n- Shipping/billing address\n- Profile picture (if uploaded)\n\nb. Usage Data:\n- Pages visited\n- Buttons clicked\n- Time spent in the app\n- Items viewed or added to favorites/cart\n\nc. Location Information:\n- If you enable location access, we may collect precise or approximate location data to enhance user experience.",
                    "3. How We Use Your Information" to "We may use your information to:\n- Process orders and manage your account\n- Improve app performance and features\n- Provide personalized recommendations\n- Send important updates and notifications\n- Respond to customer service requests\n- Detect and prevent fraud",
                    "4. Sharing Your Information" to "We do not sell your data. We may share your information with:\n- Service providers: Payment processors, delivery partners, analytics tools\n- Legal authorities: When required by law or to protect our rights and users’ safety.",
                    "5. Data Security" to "We use encryption and industry-standard security measures to protect your data. However, no method of transmission over the internet or method of electronic storage is 100% secure.",
                    "6. Your Rights & Choices" to "You have the right to:\n- Access and update your personal data\n- Request deletion of your account and data\n- Withdraw consent for specific data usage\n- Disable notifications or location access via device settings\n\nTo exercise these rights, contact us at: support@mijawharati.com",
                    "7. Third-Party Services" to "Mijawharati may integrate third-party services (e.g., Google Analytics, payment gateways). These services have their own privacy policies, which we encourage you to review.",
                    "8. Children’s Privacy" to "Our app is not intended for children under the age of 13. We do not knowingly collect data from children. If you believe we have collected such data, please contact us to delete it.",
                    "9. Changes to This Privacy Policy" to "We may update this Privacy Policy occasionally. If significant changes are made, we will notify you within the app or by email.",
                    "10. Contact Us" to "If you have any questions about this Privacy Policy, please contact us:\n\n📧 Email: mijawharati@gmail.com\n📞 Phone: +254 740 644678\n🌍 Website: www.mijawharati.com"
                )

                sections.forEach { (title, body) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CreamWhite.copy(alpha = 0.8f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = title,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = body,
                                fontSize = 16.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
