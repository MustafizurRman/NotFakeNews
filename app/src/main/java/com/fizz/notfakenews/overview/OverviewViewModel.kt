package com.fizz.notfakenews.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fizz.notfakenews.model.Article
import com.fizz.notfakenews.model.ArticleDatabase
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.model.ArticleRepository
import com.fizz.notfakenews.network.NewsApi
import kotlinx.coroutines.launch

private const val TAG = "OverViewModel"

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val _NewsData = MutableLiveData<List<Article>>()
    val newsData: LiveData<List<Article>> = _NewsData
    val repository: ArticleRepository

    init {
        val dao = ArticleDatabase.getDatabase(application).articleDao()
        repository = ArticleRepository(dao)
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            try {
                val listNewsApiData = NewsApi.retrofitService.getTopNewsUS()
                _NewsData.value = listNewsApiData.articles
                newsData.value!!.forEach {
                    repository.addArticle(
                        ArticleLocal(
                            0,
                            it.title!!,
                            it.author!!,
                            it.content!!,
                            it.description!!,
                            it.publishedAt,
                            "topNews",
                            false,
                            it.url,
                            it.urlToImage
                        )
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        }
    }
}