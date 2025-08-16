package com.kunji.mijawharati.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.kunji.mijawharati.navigation.ROUT_LANDING
import com.kunji.mijawharati.ui.theme.*

@Composable
fun HomeScreen(navcontoller: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_16),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Start your story in style",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = EmeraldGreen,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Discover beauty redefined through timeless jewellery — start your journey in style and explore your collection today.",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                color = CreamWhite,
                textAlign = TextAlign.Center,

            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    navcontoller.navigate(ROUT_LANDING)
                },
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Get Started", color = CreamWhite)
            }

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navcontoller = rememberNavController())
}


