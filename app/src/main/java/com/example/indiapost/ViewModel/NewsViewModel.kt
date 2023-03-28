package com.example.indiapost.ViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indiapost.api.RetrofitInstance
import com.example.indiapost.models.Article
import com.example.indiapost.models.News
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {

  val TAG:String = "API Data"
  var trendingListResponse: List<News> by mutableStateOf(listOf())
  private var _newsListResponse: List<News> by mutableStateOf(listOf())
  val newsListResponse: List<News> get() = _newsListResponse
  private var _sportsListResponse: List<News> by mutableStateOf(listOf())
  val sportsListResponse: List<News> get() = _sportsListResponse
  private var _financeListResponse: List<News> by mutableStateOf(listOf())
  val financeListResponse: List<News> get() = _financeListResponse
  var errorMessage: String by mutableStateOf("")

  fun getTrendingList() {
    viewModelScope.launch {
      val apiService = RetrofitInstance.getInstance()
      try {
        val newsList = apiService.getTrending()
        var dummyList: List<News> = listOf(newsList)
        trendingListResponse = dummyList
      } catch (e: Exception) {
        errorMessage = e.message.toString()
        Log.d(TAG,errorMessage)
      }
    }
  }

  fun getNewsList() {
    viewModelScope.launch {
      val apiService = RetrofitInstance.getInstance()
      try {
        val newsList = apiService.getNews()
        var dummyList: List<News> = listOf(newsList)
        _newsListResponse = dummyList
      } catch (e: Exception) {
        errorMessage = e.message.toString()
        Log.d(TAG,errorMessage)
      }
    }
  }

  fun getSportsList() {
    viewModelScope.launch {
      val apiService = RetrofitInstance.getInstance()
      try {
        val newsList = apiService.getSports()
        var dummyList: List<News> = listOf(newsList)
        _sportsListResponse = dummyList
      } catch (e: Exception) {
        errorMessage = e.message.toString()
        Log.d(TAG,errorMessage)
      }
    }
  }

  fun getFinanceList() {
    viewModelScope.launch {
      val apiService = RetrofitInstance.getInstance()
      try {
        val newsList = apiService.getFinance()
        var dummyList: List<News> = listOf(newsList)
        _financeListResponse = dummyList
      } catch (e: Exception) {
        errorMessage = e.message.toString()
        Log.d(TAG,errorMessage)
      }
    }
  }

}


