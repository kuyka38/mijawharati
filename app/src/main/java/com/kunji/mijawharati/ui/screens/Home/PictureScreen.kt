package com.kunji.mijawharati.ui.screens.picture

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
import com.kunji.mijawharati.navigation.ROUT_HOME
import com.kunji.mijawharati.navigation.ROUT_ONBOARDING
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen

@Composable
fun PictureScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_7),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "WELCOME TO MIJAWHARATI",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = EmeraldGreen,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Paragraph text
            Text(
                text = "A celebration of beauty, culture, and self-expression through timeless jewellery — embrace elegance crafted for every occasion..",
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                color = CreamWhite,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Row to align button bottom-right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate(ROUT_ONBOARDING) },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                    modifier = Modifier
                        .wrapContentSize()
                        .height(40.dp)
                ) {
                    Text("Next", color = CreamWhite, fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PictureScreenPreview() {
    PictureScreen(navController = rememberNavController())
}
