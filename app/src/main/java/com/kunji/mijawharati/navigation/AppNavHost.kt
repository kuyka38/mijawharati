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
import com.kunji.mijawharati.data.ContactDatabase
import com.kunji.mijawharati.data.UserDatabase
import com.kunji.mijawharati.repository.ContactRepository
import com.kunji.mijawharati.repository.UserRepository
import com.kunji.mijawharati.ui.screens.about.AboutScreen
import com.kunji.mijawharati.ui.screens.auth.LoginScreen
import com.kunji.mijawharati.ui.screens.intent.IntentScreen
import com.kunji.mijawharati.ui.screens.Home.HomeScreen
import com.kunji.mijawharati.ui.screens.admin.AdminDashboardScreen
import com.kunji.mijawharati.ui.screens.ladies.SplashScreen
import com.kunji.mijawharati.viewmodel.AuthViewModel
import com.kunji.mijawharati.ui.screens.auth.RegisterScreen
import com.kunji.mijawharati.ui.screens.items.AnkletsScreen
import com.kunji.mijawharati.ui.screens.items.BraceletsScreen
import com.kunji.mijawharati.ui.screens.items.EarringsScreen
import com.kunji.mijawharati.ui.screens.items.NecklacesScreen
import com.kunji.mijawharati.ui.screens.items.RingsScreen
import com.kunji.mijawharati.ui.screens.items.WatchesScreen
import com.kunji.mijawharati.ui.screens.landing.LandingScreen
import com.kunji.mijawharati.ui.screens.onboarding.OnboardingScreen
import com.kunji.mijawharati.ui.screens.picture.PictureScreen
import com.kunji.mijawharati.ui.screens.privacy.PrivacyScreen
import com.kunji.mijawharati.ui.screens.products.AddProductScreen
import com.kunji.mijawharati.ui.screens.products.CartScreen
import com.kunji.mijawharati.ui.screens.products.EditProductScreen
import com.kunji.mijawharati.ui.screens.products.FavouriteScreen
import com.kunji.mijawharati.ui.theme.screens.contact.UploadContactScreen
import com.kunji.mijawharati.ui.theme.screens.contact.ViewContactScreen
import com.kunji.mijawharati.viewmodel.ContactViewModel
import com.kunji.mijawharati.viewmodel.ProductViewModel
import com.kunji.mijawharati.ui.screens.products.ProductScreen
import com.kunji.mijawharati.ui.screens.profile.ProfileScreen
import com.kunji.swaggy.ui.screens.products.ProductListScreen


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_ADMINDASHBOARD,
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

        composable(ROUT_PICTURE) {
            PictureScreen(navController)
        }


        composable(ROUT_CART) {
            CartScreen(navController, productViewModel)
        }

        composable(ROUT_ONBOARDING) {
            OnboardingScreen(navController)
        }

        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }

        composable(ROUT_PRODUCT_SCREEN_LIST) {
            ProductScreen(navController, productViewModel)
        }



        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_LANDING) {
            LandingScreen(navController)
        }

        composable(ROUT_ADMINDASHBOARD) {
            AdminDashboardScreen(navController)
        }

        composable(ROUT_ADMINDASHBOARD) {
            AdminDashboardScreen(navController)
        }

        composable(ROUT_NECKLACES) {
            NecklacesScreen(navController)
        }

        composable(ROUT_BRACELETS) {
            BraceletsScreen(navController)
        }

        composable(ROUT_RINGS) {
            RingsScreen(navController)
        }

        composable(ROUT_EARRINGS) {
            EarringsScreen(navController)
        }

        composable(ROUT_WATCHES) {
            WatchesScreen(navController)
        }

        composable(ROUT_ANKLETS) {
            AnkletsScreen(navController)
        }

        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }

        composable(ROUT_FAVORITES) {
            FavouriteScreen(navController,productViewModel)
        }
        composable(ROUT_PRIVACY) {
            PrivacyScreen(navController)
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


        //CONTACT

        // Initialize Contact Database and ViewModel
        val contactDatabase = ContactDatabase.getDatabase(context)
        val contactRepository = ContactRepository(contactDatabase.contactDao())
        val contactViewModel = ContactViewModel(contactRepository)

        composable(ROUT_UPLOAD_CONTACT) {
            UploadContactScreen(navController, contactViewModel)
        }


        composable(ROUT_VIEW_CONTACT) {
            ViewContactScreen(navController, contactViewModel) { id ->
                navController.navigate("upload_contact?id=$id")
            }
        }












    }




}









