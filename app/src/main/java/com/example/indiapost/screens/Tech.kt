package com.example.indiapost.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.indiapost.R
import com.example.indiapost.ViewModel.NewsViewModel
import com.example.indiapost.models.Article
import com.example.indiapost.models.News
import android.text.format.DateFormat;
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import com.example.indiapost.models.Screens
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TechScreen(navController: NavHostController, modifier: Modifier = Modifier)  {
    val newsViewModel :NewsViewModel = viewModel()

    LaunchedEffect(key1 = Unit){
        newsViewModel.getNewsList()
    }
    if(newsViewModel.newsListResponse.isNotEmpty()) {
        println("mush tech" +newsViewModel.newsListResponse[0].articles[0].title)
        Surface{
            NewsCardList(news = newsViewModel.newsListResponse[0], navController)
        }
    }else{
        ShimmerListNews( modifier = modifier)
    }
}

@Composable
fun NewsCardList(news: News, navController: NavHostController, modifier: Modifier =Modifier) {
    val articles: List<Article> = remember {
        news.articles
    }
    LazyColumn {
        items(articles) { article ->
            NewsCard(article, navController)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(article: Article,navController: NavHostController, modifier: Modifier = Modifier){

    if(article.urlToImage!=null && article.url!=null) {
        Card(
            modifier = modifier.clickable {
                navController.navigate(Screens.ViewArticle.withArgs(URLEncoder.encode(article.url)))
            }
        ) {
            ListItem(
                modifier = modifier,
                icon = {
                    Image(
                        painter = rememberAsyncImagePainter(article.urlToImage),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .wrapContentSize()
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .size(90.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                    )
                },
                text = { Text(article.title) },
                secondaryText = { Text(MetaData(article)) }
            )
        }
    }
}

@Composable
fun MetaData(article: Article): String{
    val divider = " • "
    var date:String? = ParseDate(date = article.publishedAt)
    var resultString = buildAnnotatedString {
        if (date != null) {
            append(date)
        }
        append(divider)
        append( stringResource(R.string.duartion, (1..10).shuffled().last()))
    }
    return resultString.toString()
}

@Composable
fun ParseDate(date: String) : String?{
    var dateText: String? = null
        var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    try {
        var formatted: Date = dateFormat.parse(date) as Date
        val day = DateFormat.format("dd", formatted) as String // 20
        val monthString = DateFormat.format("MMM", formatted) as String // Jun
        dateText = day +" "+monthString
    }catch (e:java.lang.Exception){

    }
    return dateText
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHomeScreen() {
//    IndiaPostTheme {
//        NewsScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewNewsCard(){
////    val newsViewModel by viewModels<NewsViewModel>()
////    var News = NewsModelObj.getTrendingList()
//
//    var article:Article = Article(
//        author = "Alice",
//        content ="the nigella kitchen..",
//        description = "des",
//        publishedAt = "2023-01-10T16:05:24Z",
//        source =  Source("23423","Notebookcheck.net"),
//        title = "iQOO 11 becomes India's first Snapdragon 8 Gen 2-powered, 144Hz display flagship smartphone",
//        url = "https://www.notebookcheck.net/iQOO-11-becomes-India-s-first-Snapdragon-8-Gen-2-powered-144Hz-display-flagship-smartphone.680572.0.html",
//        urlToImage = "https://images.indianexpress.com/2023/01/bengaluru-metro-pillar-victim-blurred.jpg"
//    )
//    var articles: List<Article> = listOf(article)
//    var news = News(articles,"",23423)
//    if (news != null) {
//        NewsCard(article = news.articles[0])
//    }
//    else{
//        Text("News is nul")
//    }
//}