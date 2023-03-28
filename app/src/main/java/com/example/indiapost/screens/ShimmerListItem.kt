package com.example.indiapost.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.indiapost.models.Screens
import java.net.URLEncoder

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF706B6B),
                Color(0xFF3C3838),
                Color(0xFF706B6B)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )

        .onGloballyPositioned {
            size = it.size
        }

}

@Composable
fun ShimmerListNews(modifier: Modifier){
    LazyColumn(modifier = modifier){
        items(20){
            ShimmerListItemNews(isLoading = true, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShimmerListItemNews(
    isLoading: Boolean,
    modifier: Modifier
) {
    if(isLoading){
        Card()
        {
            ListItem(
                modifier = Modifier,
                icon = {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .shimmerEffect()
                    )
                },
                text = { Text(
                    text = "",
                    modifier = modifier
                        .shimmerEffect()
                        .size(10.dp)
                ) },
                secondaryText = { Text(
                    text = "",
                    modifier = modifier
                        .shimmerEffect()
                        .size(10.dp)
                )}
            )
        }
    }
}


@Composable
fun ShimmerListCard(modifier: Modifier){
    LazyColumn(modifier = modifier){
        items(20){
            ShimmerListItemCard(isLoading = true, modifier = modifier)
        }
    }
}

@Composable
fun ShimmerListItemCard(
    isLoading: Boolean,
    modifier: Modifier
) {
    if(isLoading){
        Card(modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .heightIn(180.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}



