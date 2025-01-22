package com.example.indiapost

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.indiapost.models.Screens

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        topBar = { TopBar()}

    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController = navController)
        }
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        modifier =  Modifier
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color.White,Color.Black)
                )
            ),
        title = { Text(text = "INDIA POST") }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier){

    val screens = listOf(
        Screens.Trending,
        Screens.Tech,
        Screens.Sports,
        Screens.Finance
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = modifier.background(MaterialTheme.colors.primary)
    ) {
        screens.forEach{ screen ->
            AddItem(screen, currentDestination, navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screens,
    currentDestination: NavDestination?,
    navController: NavHostController) {

    val selected = screen.route == currentDestination?.route
    BottomNavigationItem(
        selected = selected,
        onClick = { navController.navigate(screen.route){
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        } },
        label = {
            Text(text = screen.title) },
        icon = {
            if(selected) {
                Image(painter = painterResource(id = screen.activeIcon), contentDescription = null)
            }else{
                Image(painter = painterResource(id = screen.inActiveIcon), contentDescription = null)
            }
        }
    )
}
