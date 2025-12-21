package com.design.template.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.design.template.DocumentScannerApp

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Main.route
    ) {
        composable(route = AppRoute.Main.route) {
            DocumentScannerApp()
        }
    }
}

sealed class AppRoute(val route: String) {
    data object Main : AppRoute("main")
}
