package com.example.indiapost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.indiapost.models.Screens
import com.example.indiapost.screens.ArticleScreen
import com.example.indiapost.screens.FinanceScreen
import com.example.indiapost.screens.SportsScreen
import com.example.indiapost.screens.TechScreen
import com.example.indiapost.screens.TrendingScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Trending.route,
    ) {
        composable(route = Screens.Trending.route) {
            TrendingScreen(navController)
        }
        composable(route = Screens.Tech.route) {
            TechScreen(navController)
        }
        composable(route = Screens.Sports.route) {
            SportsScreen(navController)
        }
        composable(route = Screens.Finance.route) {
            FinanceScreen(navController)
        }
        composable(
            route = Screens.ViewArticle.route + "/{url}",
            arguments = listOf(
                navArgument(name = "url") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { entry ->
            ArticleScreen(url = entry.arguments?.getString("url")!!)
        }
    }
}
