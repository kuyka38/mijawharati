package com.kunji.mijawharati.ui.screens.ladies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.navigation.ROUT_LOGIN
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {

    val coroutine = rememberCoroutineScope()
    coroutine.launch {
        delay(2000)
        navController.navigate(ROUT_LOGIN)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(EmeraldGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Title
            Text(
                text = "MiJawharati",
                fontSize = 62.sp,
                fontWeight = FontWeight.SemiBold,
                color = CreamWhite,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "Where elegance meets style",
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = CreamWhite,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Cursive
            )


            Spacer(modifier = Modifier.height(60.dp) )

            CircularProgressIndicator(

                color = CreamWhite


            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
