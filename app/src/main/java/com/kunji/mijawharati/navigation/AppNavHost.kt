package com.kunji.mijawharati.navigation

import WomenScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.ui.screens.LoginScreen
import com.kunji.mijawharati.ui.screens.category.CategoryScreen
import com.kunji.mijawharati.ui.screens.intent.IntentScreen
import com.kunji.mijawharati.ui.screens.onboarding.HomeScreen
import com.kunji.mijawharati.ui.screens.women.SplashScreen
import com.kunji.mijawharati.ui.screens.men.MenScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUT_CATEGORY) {
            CategoryScreen(navController)
        }

        composable(ROUT_WOMEN) {
            WomenScreen(navController)
        }

        composable(ROUT_MEN) {
            MenScreen(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }
    }
}






