package com.rizfan.mentara.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object History : Screen("history")
    object Profile : Screen("profile")
    object Welcome : Screen ("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
}
