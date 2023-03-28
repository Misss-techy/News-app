package com.example.indiapost.api

import androidx.lifecycle.LiveData
import com.example.indiapost.models.News
import com.example.indiapost.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

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
        from: String = "2023-03-19",
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News

    @GET("v2/top-headlines")
    suspend fun getSports(
        @Query("language")
        language:String = "en",
        @Query("domains")
        domain:String = "espn.com",
        @Query("from")
        from: String = "2023-03-19",
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News

    @GET("v2/top-headlines")
    suspend fun getFinance(
        @Query("language")
        language:String = "en",
        @Query("domains")
        domain:String = "moneycontrol.com",
        @Query("from")
        from: String = "2023-03-19",
        @Query("apiKey")
        apiKey:String = API_KEY
    ) : News


}