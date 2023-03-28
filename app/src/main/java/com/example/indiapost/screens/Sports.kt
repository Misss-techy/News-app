package com.example.indiapost.screens

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.indiapost.ViewModel.NewsViewModel
import com.example.indiapost.ui.theme.IndiaPostTheme

@Composable
fun SportsScreen(navController: NavHostController, modifier: Modifier = Modifier){
    val sportsViewModel : NewsViewModel = viewModel()
    LaunchedEffect(key1 = Unit){
        sportsViewModel.getSportsList()
    }
    if(sportsViewModel.sportsListResponse.isNotEmpty()) {
        println("mush sports" +sportsViewModel.sportsListResponse[0].articles[0].title)
        Surface{
            NewsCardList(news = sportsViewModel.sportsListResponse[0], navController)
        }
    }else{
        ShimmerListNews( modifier = modifier)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSportsScreen() {
//    IndiaPostTheme {
//        SportsScreen()
//    }
//}