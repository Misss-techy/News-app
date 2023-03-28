package com.example.indiapost.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indiapost.api.RetrofitInstance
import com.example.indiapost.models.Article
import com.example.indiapost.models.News
import kotlinx.coroutines.launch


class NewsViewModel: ViewModel() {

        var trendingListResponse: List<News> by mutableStateOf(listOf())
        private var _newsListResponse : List<News> by mutableStateOf(listOf())
        val newsListResponse: List<News> get() = _newsListResponse
        var errorMessage: String by mutableStateOf("")


        fun getTrendingList() {
            viewModelScope.launch {
                val apiService = RetrofitInstance.getInstance()
                try{
                    val newsList = apiService.getTrending()
                    var dummyList: List<News> = listOf(newsList)
                    trendingListResponse = dummyList
                }catch (e:Exception) {
                    errorMessage = e.message.toString()
                }
            }
        }

    fun getNewsList(){
        viewModelScope.launch {
            val apiService = RetrofitInstance.getInstance()
            try{
                val newsList = apiService.getNews()
                var dummyList: List<News> = listOf(newsList)
                _newsListResponse = dummyList
            }catch (e:Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

  fun getSports(){
    viewModelScope.launch {
      val apiService = RetrofitInstance.getInstance()
      try{
        val newsList = apiService.getNews()
        var dummyList: List<News> = listOf(newsList)
        //println("mush api home "+newsList.articles[0].author)
        //newsListResponse = dummyList
        _newsListResponse = dummyList
      }catch (e:Exception) {
        errorMessage = e.message.toString()
        println("mush api home "+errorMessage)
      }
    }
  }

}


