package com.kunji.mijawharati.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.data.UserDatabase
import com.kunji.mijawharati.repository.UserRepository
import com.kunji.mijawharati.ui.screens.about.AboutScreen
import com.kunji.mijawharati.ui.screens.auth.LoginScreen
import com.kunji.mijawharati.ui.screens.category.CategoryScreen
import com.kunji.mijawharati.ui.screens.contact.ContactsScreen
import com.kunji.mijawharati.ui.screens.intent.IntentScreen
import com.kunji.mijawharati.ui.screens.onboarding.HomeScreen
import com.kunji.mijawharati.ui.screens.men.MenScreen
import com.kunji.mijawharati.ui.screens.ladies.SplashScreen
import com.kunji.mijawharati.ui.screens.ladies.LadiesScreen
import com.kunji.mijawharati.viewmodel.AuthViewModel
import com.kunji.mijawharati.ui.screens.auth.RegisterScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {


    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }


        composable(ROUT_CATEGORY) {
            CategoryScreen(navController)
        }


        composable(ROUT_MEN) {
            MenScreen(navController)
        }

        composable(ROUT_LADIES) {
            LadiesScreen(navController)
        }


        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }

        composable(ROUT_CONTACT) {
            ContactsScreen(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }


        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }






    }
}






