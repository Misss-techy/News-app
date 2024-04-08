package com.example.indiapost.api

import com.example.indiapost.models.News
import com.example.indiapost.util.Constants.Companion.API_KEY
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.time.ExperimentalTime
import kotlin.time.days

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getTrending(
        @Query("country")
        countryCode:String = "IN",
        @Query("page")
        page:Int = 2,
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News

    @GET("v2/everything")
    suspend fun getNews(
        @Query("language")
        language:String = "en",
        @Query("domains")
        domain:String = "techcrunch.com",
        @Query("from")
        from: String = getDate(),
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News

    @GET("v2/everything")
    suspend fun getSports(
        @Query("language")
        language:String = "en",
        @Query("domains")
        domain:String = "espn.com",
        @Query("from")
        from: String = getDate(),
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News

    @GET("v2/everything")
    suspend fun getFinance(
        @Query("language")
        language:String = "en",
        @Query("domains")
        domain:String = "moneycontrol.com",
        @Query("from")
        from: String = getDate(),
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News


}

@OptIn(ExperimentalTime::class)
fun getDate(): String {
    val currentDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toInstant(TimeZone.UTC)
    val threeWeeksAgo = currentDate.minus(12.days).toLocalDateTime(TimeZone.currentSystemDefault())
    return "${threeWeeksAgo.year}-${threeWeeksAgo.month}-${threeWeeksAgo.date}"
}