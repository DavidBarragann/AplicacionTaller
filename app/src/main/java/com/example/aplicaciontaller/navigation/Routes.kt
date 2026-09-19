package com.example.aplicaciontaller.navigation

sealed class Routes(val route: String){
    object Login: Routes("login")
    object Register: Routes("register")
    object Home : Routes("home")
    object Offers : Routes("offers")
    object Favorites : Routes("favorites")
    object Profile : Routes("profile")
    object Cart : Routes("cart")
}