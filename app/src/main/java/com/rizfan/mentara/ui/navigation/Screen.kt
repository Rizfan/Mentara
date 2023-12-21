package com.rizfan.mentara.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object History : Screen("history")
    object Profile : Screen("profile")
    object Welcome : Screen ("welcome")
    object Login : Screen("login")
    object Chatbot : Screen("chatbot")
    object Register : Screen("register")
    object Detail : Screen("detail/{resultId}"){
        fun createRoute(resultId: Int) = "detail/$resultId"
    }


}
