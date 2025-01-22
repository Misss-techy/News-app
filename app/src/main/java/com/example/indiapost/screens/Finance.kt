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
fun FinanceScreen(navController: NavHostController, modifier: Modifier = Modifier){
    val financeViewModel : NewsViewModel = viewModel()
    LaunchedEffect(key1 = Unit){
        financeViewModel.getFinanceList()
    }
    if(financeViewModel.financeListResponse.getOrNull(0)?.articles?.isNotEmpty() == true) {
        Surface{
            NewsCardList(news = financeViewModel.financeListResponse[0], navController)
        }
    }else{
        ShimmerListNews( modifier = modifier)
    }
}