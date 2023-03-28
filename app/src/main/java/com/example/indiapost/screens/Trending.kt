package com.example.indiapost.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.indiapost.R
import com.example.indiapost.ViewModel.NewsViewModel
import com.example.indiapost.models.Article
import com.example.indiapost.models.Screens
import com.example.indiapost.models.News
import java.net.URLEncoder
import com.example.indiapost.screens.shareLink as shareLink1


@Composable
fun TrendingScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val TrendingViewModel: NewsViewModel = viewModel()

    LaunchedEffect(key1 = Unit) {
        TrendingViewModel.getTrendingList()
    }
    if (TrendingViewModel.trendingListResponse.isNotEmpty()) {
        println("mush trending" +TrendingViewModel.trendingListResponse[0].articles[0].title)
        Surface {
            TrendingCardsList(news = TrendingViewModel.trendingListResponse[0], navController)
        }
    } else {
        ShimmerListCard(modifier = modifier)
        println("mush trending is emtpy" )
    }
}

@Composable
fun TrendingCardsList(news: News, navController: NavHostController, modifier: Modifier = Modifier) {
    var articles: List<Article> = news.articles
    LazyColumn(contentPadding = PaddingValues(vertical = 20.dp)) {
        items(articles) { article ->
            TrendingCards(article = article, navController)
        }
    }
//    Spacer(modifier = modifier.heightIn(20.dp))
}

@Composable
fun TrendingCards(
    article: Article,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

  //  if (article.urlToImage != null && article.url != null) {
        var date: String = parseMetaData(article = article)
        Card(modifier
            .clickable {
                navController.navigate(
                    Screens.ViewArticle.withArgs(URLEncoder.encode(article.url))
                )
            }
            .padding(horizontal = 20.dp, vertical = 10.dp)) {
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
                        text = date,
                        modifier = padding,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(Modifier.weight(1f))
                    var context = LocalContext.current
                    IconButton(
                        onClick = {
                            ContextCompat.startActivity(
                                context,
                                Intent.createChooser(shareLink1(url = article.url), "Share with"),
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
   // }
}

@Composable
fun parseMetaData(article: Article): String {
    val divider = " • "
    var resultString = buildAnnotatedString {
        append(article.source.name)
        append(divider)
        append(stringResource(R.string.duartion_ago, (1..10).shuffled().last()))
    }
    return resultString.toString()
}

//@Preview
//@Composable
//fun PreviewTrendingCard(){
//    var article:Article = Article(
//        author = "HT Sports Desk",
//        content ="the nigella kitchen..",
//        description = "des",
//        publishedAt = "2023-01-10T16:05:24Z",
//        source =  Source("23423","Notebookcheck.net"),
//        title = "Starc out of 1st Test, Australia announce squad for India tour with 2 surprises - Hindustan Times",
//        url = "https://www.notebookcheck.net/iQOO-11-becomes-India-s-first-Snapdragon-8-Gen-2-powered-144Hz-display-flagship-smartphone.680572.0.html",
//        urlToImage = "https://images.hindustantimes.com/img/2023/01/11/1600x900/Australia-South-Africa-Cricket-1_1673402118688_1673402118688_1673402321376_1673402321376.jpg"
//    )
//    var articles: List<Article> = listOf(article)
//    var news = News(articles,"",23423)
//    if (news != null) {
//        TrendingCards(article = news.articles[0])
//    }
//    else{
//        Text("News is nul")
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTrendingScreen() {
//    IndiaPostTheme {
//        TrendingScreen()
//    }
//}

