package com.example.indiapost.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.indiapost.models.Article
import com.example.indiapost.models.Screens
import com.example.indiapost.util.parseDate
import java.net.URLEncoder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(article: Article, navController: NavHostController, modifier: Modifier = Modifier) {

    if(article.url.isNotEmpty()) {
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
                secondaryText = { parseDate(date = article.publishedAt)?.let { Text(it) } }
            )
        }
    }
}