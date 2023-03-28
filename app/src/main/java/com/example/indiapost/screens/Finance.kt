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
fun FinanceScreen(navController: NavHostController, modifier: Modifier = Modifier){
    val financeViewModel : NewsViewModel = viewModel()
    LaunchedEffect(key1 = Unit){
        financeViewModel.getFinanceList()
    }
    if(financeViewModel.financeListResponse.isNotEmpty()) {
        println("mush finance" +financeViewModel.financeListResponse[0].articles[0].title)
        Surface{
            NewsCardList(news = financeViewModel.financeListResponse[0], navController)
        }
    }else{
        ShimmerListNews( modifier = modifier)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileScreen() {
//    IndiaPostTheme {
//        FinanceScreen()
//    }
//}