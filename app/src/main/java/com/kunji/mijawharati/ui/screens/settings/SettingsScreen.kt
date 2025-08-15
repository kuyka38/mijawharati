package com.kunji.mijawharati.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mijawharati") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUT_LANDING) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Account", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                MijawharatiItem(
                    icon = Icons.Default.Person,
                    title = "Profile",
                    onClick = { /* Navigate to profile */ }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Preferences", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                MijawharatiItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    onClick = { /* Handle */ }
                )

                // 👇 Replaced Language item with a dropdown version
                LanguageSettingItem()

                Spacer(modifier = Modifier.height(16.dp))

                Text("Others", fontWeight = FontWeight.Bold, fontSize = 18.sp)


                MijawharatiItem(
                    icon = Icons.Default.ExitToApp,
                    title = "Logout",
                    onClick = { /* Logout */ }
                )
            }
        }
    )
}

@Composable
fun MijawharatiItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp,
        color = Color(0xFFF9F9F9)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = EmeraldGreen,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, fontSize = 16.sp)
        }
    }
}

@Composable
fun LanguageSettingItem() {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("English (USA)", "English (UK)" )
    var selectedLanguage by remember { mutableStateOf(languages[0]) }

    Column {
        MijawharatiItem(
            icon = Icons.Default.Build,
            title = "Language: $selectedLanguage",
            onClick = { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = { Text(language) },
                    onClick = {
                        selectedLanguage = language
                        expanded = false
                        // TODO: persist selection if needed
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController())
}
