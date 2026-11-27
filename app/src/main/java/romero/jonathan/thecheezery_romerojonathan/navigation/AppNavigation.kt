package romero.jonathan.thecheezery_romerojonathan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import romero.jonathan.thecheezery_romerojonathan.screens.CartScreen
import romero.jonathan.thecheezery_romerojonathan.screens.LoginScreen
import romero.jonathan.thecheezery_romerojonathan.screens.MenuScreen
import romero.jonathan.thecheezery_romerojonathan.screens.ProductsScreen
import romero.jonathan.thecheezery_romerojonathan.screens.WelcomeScreen
import romero.jonathan.thecheezery_romerojonathan.viewmodel.CartViewModel

object Routes {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val MENU = "menu"
    const val PRODUCTS = "products/{category}"
    const val CART = "cart"
    fun products(category: String) = "products/$category"
}

@Composable
fun AppNavigation(cartViewModel: CartViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.WELCOME, modifier = modifier) {
        composable(Routes.WELCOME) {
            WelcomeScreen(onGetStarted = { navController.navigate(Routes.LOGIN) })
        }
        composable(Routes.LOGIN) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Routes.MENU) {
                    popUpTo(Routes.WELCOME) { inclusive = true }
                }
            })
        }
        composable(Routes.MENU) {
            MenuScreen(
                onCategoryClick = { navController.navigate(Routes.products(it.name)) },
                onCartClick = { navController.navigate(Routes.CART) }
            )
        }
        composable(
            route = Routes.PRODUCTS,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            ProductsScreen(cartViewModel = cartViewModel)
        }
        composable(Routes.CART) {
            CartScreen(cartViewModel = cartViewModel)
        }
    }
}