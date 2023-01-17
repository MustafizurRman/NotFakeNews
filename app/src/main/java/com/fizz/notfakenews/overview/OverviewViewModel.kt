package com.fizz.notfakenews.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fizz.notfakenews.model.Article
import com.fizz.notfakenews.network.NewsApi
import kotlinx.coroutines.launch

private const val TAG= "OverViewModel"

enum class NewsApiStatus { LOADING, ERROR, DONE }
class OverviewViewModel(application: Application):AndroidViewModel(application) {
    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus> = _status
    private val _NewsData=MutableLiveData<List<Article>>()
    val newsData: LiveData<List<Article> > = _NewsData

    init {
        getNews()
    }
    private fun getNews(){
        viewModelScope.launch {
            try {
                val listNewsApiData = NewsApi.retrofitService.getTopNewsUS()
                _NewsData.value = listNewsApiData.articles
                Log.d(TAG,"${newsData.value}")
            }
            catch (e:Exception){
                Log.e(TAG,"${e.message}")
            }
        }
    }
}