package com.rizfan.mentara

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rizfan.mentara.data.utils.RouteConst
import com.rizfan.mentara.ui.navigation.NavigationItem
import com.rizfan.mentara.ui.navigation.Screen
import com.rizfan.mentara.ui.screen.chatbot.ChatBotScreen
import com.rizfan.mentara.ui.screen.history.HistoryScreen
import com.rizfan.mentara.ui.screen.homescreen.HomeScreen
import com.rizfan.mentara.ui.screen.login.LoginScreen
import com.rizfan.mentara.ui.screen.profile.ProfileScreen
import com.rizfan.mentara.ui.screen.register.RegisterScreen
import com.rizfan.mentara.ui.screen.result.ResultScreen
import com.rizfan.mentara.ui.screen.welcome.WelcomePage

@Composable
fun MentaraApp(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Profile.route || currentRoute == Screen.History.route){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                val context = LocalContext.current
                HomeScreen(
                    navigateToWelcome = {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screen.Welcome.route)
                        }
                    },
                    onTopUpButtonClicked = {
                        topUp(context)
                    },
                    navigateToChatbot = {
                        navController.navigate(Screen.Chatbot.route)
                    }
                )
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(Screen.Welcome.route){
                WelcomePage(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.Chatbot.route){
                ChatBotScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.Login.route){
                LoginScreen(
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    navigateToHome = {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screen.Home.route)
                        }
                    }
                )
            }
            composable(Screen.Register.route){
                RegisterScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.History.route){
                HistoryScreen(
                    navigateToDetail = {resultId ->
                        navController.navigate(Screen.Detail.createRoute(resultId))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(RouteConst.RESULT_ID) { type = NavType.IntType })
                ){
                val resultId = it.arguments?.getInt(RouteConst.RESULT_ID)?: -1
                ResultScreen(
                    resultId = resultId,
                    navigateBack = { navController.navigateUp() },
                )
            }
        }

    }

}

@Composable
private fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_history),
                icon = Icons.Default.SwapVert,
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(icon = {
                Icon(
                    imageVector = item.icon, contentDescription = item.title
                )
            },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


private fun topUp(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/6282256349900?text=Hallo%2C%20Aku%20ingin%20melakukan%20Top%20Up%20Mentara%20Balance!"))

    context.startActivity(
        intent
    )
}