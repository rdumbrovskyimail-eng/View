package com.design.template.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.design.template.DocumentScannerApp

/**
 * Central place for app navigation
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Main.route
    ) {
        composable(route = AppRoute.Main.route) {
            MainScreen()
        }
    }
}

/**
 * Screens wrapper
 * (separation between navigation and UI root)
 */
@Composable
private fun MainScreen() {
    DocumentScannerApp()
}

/**
 * Typed routes — safe & scalable
 */
sealed class AppRoute(val route: String) {
    data object Main : AppRoute("main")
}
