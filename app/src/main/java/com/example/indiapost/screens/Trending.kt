package com.example.indiapost.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.indiapost.R
import com.example.indiapost.ViewModel.NewsViewModel
import com.example.indiapost.models.Article
import com.example.indiapost.models.News
import com.example.indiapost.models.Screens
import com.example.indiapost.screens.components.ShimmerListCard
import java.net.URLEncoder


@Composable
fun TrendingScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val trendingViewModel: NewsViewModel = viewModel()

    LaunchedEffect(key1 = Unit) {
        trendingViewModel.getTrendingList()
    }
    if (!trendingViewModel.trendingListResponse.getOrNull(0)?.articles.isNullOrEmpty()) {
        Surface {
            TrendingCardsList(news = trendingViewModel.trendingListResponse[0], navController)
        }
    } else {
        ShimmerListCard(modifier = modifier)
    }
}

@Composable
fun TrendingCardsList(news: News, navController: NavHostController) {
    val articles: List<Article> = news.articles
    LazyColumn(contentPadding = PaddingValues(vertical = 20.dp)) {
        items(articles) { article ->
            TrendingCards(article = article, navController)
        }
    }
}

@Composable
fun TrendingCards(
    article: Article,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .clickable {
                navController.navigate(
                    Screens.ViewArticle.withArgs(URLEncoder.encode(article.url))
                )
            }
            .padding(horizontal = 20.dp, vertical = 10.dp))
    {
        Column(modifier = modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize()
                    .heightIn(180.dp),
                contentScale = ContentScale.Crop
            )
            val padding = modifier.padding(horizontal = 16.dp)
            Spacer(modifier = modifier.heightIn(16.dp))
            Text(
                text = article.title,
                modifier = padding,
                style = MaterialTheme.typography.h6
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    modifier = padding,
                    style = MaterialTheme.typography.body2
                )
                Spacer(Modifier.weight(1f))
                val context = LocalContext.current
                IconButton(
                    onClick = {
                        ContextCompat.startActivity(
                            context,
                            Intent.createChooser(
                                shareLink(url = article.url), "Share with"),
                            null
                        )
                },
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(end = 8.dp)
                    )
                }
            }
        }
}
