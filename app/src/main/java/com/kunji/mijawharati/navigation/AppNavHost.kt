package com.kunji.mijawharati.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import com.kunji.mijawharati.ui.screens.landing.LandingScreen
import com.kunji.mijawharati.ui.screens.products.AddProductScreen
import com.kunji.mijawharati.ui.screens.products.EditProductScreen
import com.kunji.mijawharati.ui.screens.products.ProductListScreen

import com.kunji.swaggy.viewmodel.ProductViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    productViewModel: ProductViewModel = viewModel(),

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

        composable("AboutScreen") {
            AboutScreen(navController)
        }


        composable(ROUT_CATEGORY) {
            CategoryScreen(navController)
        }


        composable("MenScreen") {
            MenScreen(navController)
        }

        composable("LadiesScreen") {
            LadiesScreen(navController)
        }


        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }

        composable("ContactsScreen") {
            ContactsScreen(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_LANDING) {
            LandingScreen(navController)
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


        //CrudProducts
        //Products
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }






    }
}






