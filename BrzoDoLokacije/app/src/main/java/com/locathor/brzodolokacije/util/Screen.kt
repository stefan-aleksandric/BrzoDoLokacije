package com.locathor.brzodolokacije.util

sealed class Screen(val route:String){
    object LoginScreen : Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object PostsScreen: Screen("posts_screen")
}
