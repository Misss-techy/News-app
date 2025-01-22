package com.example.indiapost.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.indiapost.ViewModel.NewsViewModel
import com.example.indiapost.screens.components.NewsCardList
import com.example.indiapost.screens.components.ShimmerListNews

@Composable
fun SportsScreen(navController: NavHostController, modifier: Modifier = Modifier){
    val sportsViewModel : NewsViewModel = viewModel()
    LaunchedEffect(key1 = Unit){
        sportsViewModel.getSportsList()
    }
    if(sportsViewModel.sportsListResponse.isNotEmpty()) {
        Surface{
            sportsViewModel.sportsListResponse.getOrNull(0)
                ?.let { NewsCardList(news = it, navController) }
        }
    } else {
        ShimmerListNews( modifier = modifier)
    }
}