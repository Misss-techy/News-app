package com.example.indiapost.models

import androidx.annotation.DrawableRes
import com.example.indiapost.R

sealed class Screens(
    val route:String,
    val title:String,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inActiveIcon: Int

){
    object Trending: Screens(
        route = "trending",
        title = "Trending",
        activeIcon = R.drawable.ic_trending,
        inActiveIcon = R.drawable.ic_trending_outline
    )
    object Tech: Screens(
        route = "tech",
        title = "Tech",
        activeIcon = R.drawable.ic_tech,
        inActiveIcon = R.drawable.ic_tech_outline
    )
    object Sports: Screens(
        route = "Sports",
        title = "Sports",
        activeIcon = R.drawable.ic_sports,
        inActiveIcon = R.drawable.ic_sports_outline
    )
    object Finance: Screens(
        route = "Finance",
        title = "Finance",
        activeIcon = R.drawable.ic_finance,
        inActiveIcon = R.drawable.ic_finance_outline
    )

    object ViewArticle: Screens(
        route = "viewArticle",
        title = "ViewArticle",
        activeIcon = 0,
        inActiveIcon = 0,
    )

    fun withArgs(vararg args: String) : String{
        println("mush withArgs called")
        var str:String = buildString{
            append(route)
            args.forEach{ arg ->
                if(arg!=null){
                append("/$arg")}
                else{
                    append("/")
                }
            }
        }
        return str
    }
}
