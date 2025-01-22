package com.example.indiapost.screens.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.indiapost.models.Article
import com.example.indiapost.models.News

@Composable
fun NewsCardList(news: News, navController: NavHostController) {
    val articles: List<Article> = remember {
        news.articles
    }
    LazyColumn {
        items(articles) { article ->
            NewsCard(article, navController)
        }
    }
}

