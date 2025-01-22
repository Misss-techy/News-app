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
fun TechScreen(navController: NavHostController, modifier: Modifier = Modifier)  {
    val newsViewModel :NewsViewModel = viewModel()
    LaunchedEffect(key1 = Unit){
        newsViewModel.getNewsList()
    }
    if(newsViewModel.newsListResponse.isNotEmpty()) {
        Surface{
            NewsCardList(news = newsViewModel.newsListResponse[0], navController)
        }
    } else {
        ShimmerListNews( modifier = modifier)
    }
}