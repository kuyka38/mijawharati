package com.kunji.mijawharati.ui.screens.contact

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.ui.theme.CreamWhite

@Composable
fun ContactsScreen(navController: NavController) {

    val mContext = LocalContext.current


    val bgImage: Painter = painterResource(id = R.drawable.back1)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = bgImage, contentScale = ContentScale.Crop)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(CreamWhite.copy(alpha = 0.85f), shape = MaterialTheme.shapes.medium)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contact Us",
                fontSize = 28.sp,
                color = Color(0xFF046A38), // Emerald green
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "We usually get back within 24 business hours.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("I need help regarding...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Message") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // TODO: Submit action
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF046A38)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SUBMIT", color = Color.White)

            }


            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Or",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                    val callIntent= Intent(Intent.ACTION_DIAL)
                    callIntent.data="tel:0758936777".toUri()
                    mContext.startActivity(callIntent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF046A38)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("CALL US NOW", color = Color.White)

            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactsScreenPreview() {
    ContactsScreen(navController = rememberNavController())
}
