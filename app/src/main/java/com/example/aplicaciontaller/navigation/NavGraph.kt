package com.example.aplicaciontaller.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciontaller.View.CartView
import com.example.aplicaciontaller.View.FavoritesView
import com.example.aplicaciontaller.View.HomeView
import com.example.aplicaciontaller.View.LoginView
import com.example.aplicaciontaller.View.OffersView
import com.example.aplicaciontaller.View.ProfileView
import com.example.aplicaciontaller.View.RegisterView
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController()
){
    //Verificamos si ya existe sesión activa en Firebase
    val startRoute = if(FirebaseAuth.getInstance().currentUser != null){
        Routes.Home.route
    }else{
        Routes.Login.route
    }

    // 2. Definir el navhost de manera dinámica
    NavHost(
        navController=navController,
        startDestination = startRoute,
        modifier = Modifier
    ){
        //Definir destinos
        composable(Routes.Login.route){
            LoginView(
                onNavigateToRegister = {navController.navigate(Routes.Register.route)},
                onLoginSuccess = {
                    //al ingresar con éxito definimos la ruta al HomeView
                    navController.navigate(Routes.Home.route){
                    popUpTo(Routes.Login.route){inclusive = true}}
                }
            )
        }
        //Registro
        composable(Routes.Register.route) {
            RegisterView(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Home.route){
            HomeView(
                onItemSelected = {index ->
                    navigateByBar(navController,index)
                },
                onCartClick = {
                    navController.navigate(Routes.Cart.route)
                }
            )
        }
        // Pantalla de Ofertas
        composable(Routes.Offers.route) {
            OffersView(onItemSelected = { index ->
              navigateByBar(navController, index)
            })
        }

        // Pantalla de Favoritos
        composable(Routes.Favorites.route) {
            FavoritesView(onItemSelected = { index ->
               navigateByBar(navController, index)
            })
        }
        composable(Routes.Profile.route){
            ProfileView(
                onItemSelected = {index ->
                    navigateByBar(navController, index)
                },
                onLogout = {
                    navController.navigate(Routes.Login.route){
                        popUpTo(Routes.Home.route){inclusive=true}
                    }
                }
            )
        }
        composable(Routes.Cart.route) {
            CartView(onItemSelected = { index ->
             navigateByBar(navController, index)
            },
                onBackClick = {navController.popBackStack()})
        }
    }
}

fun navigateByBar(navController: NavHostController, index:Int){
    when (index){
        0 -> navController.navigate(Routes.Home.route)
        1 -> navController.navigate(Routes.Offers.route)
        2 -> navController.navigate(Routes.Favorites.route)
        3 -> navController.navigate(Routes.Profile.route)
    }
}