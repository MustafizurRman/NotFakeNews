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
import com.fizz.notfakenews.network.RetrofitInstance.Companion.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val _newsData = MutableLiveData<List<Article>>()
    private val newsData: LiveData<List<Article>> = _newsData
    private val readAllNews: LiveData<List<ArticleLocal>>
    private val repository: ArticleRepository

    /*    val categories =
            listOf("technology")*/
    private val categories =
        listOf(
            "",
            "general",
            "technology",
            "entertainment",
            "health",
            "sports",
            "science",
            "business"
        )

    init {
        val dao = ArticleDatabase.getDatabase(application).articleDao()
        repository = ArticleRepository(dao)
        readAllNews = repository.readAllArticle
    }

    fun getNews() {
        viewModelScope.launch {
            for (category in categories) {
                if (category == "") {
                    _newsData.value = NewsApi.getNewsByCountry("us").articles
                } else {
                    _newsData.value = NewsApi.getByCategory(category).articles
                }
                try {
                    if (_newsData.value!!.isNotEmpty()) {
                        viewModelScope.launch(Dispatchers.IO) {
                            newsData.value?.let { insertLocal(category, it) }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("OverView", "${e.message}")
                }
            }
        }
    }

    private fun insertLocal(category: String, newsList: List<Article>) {
        for (news in newsList) {
            val article = ArticleLocal(
                0,
                news.title,
                news.author,
                news.content,
                news.description,
                news.publishedAt,
                category,
                false,
                news.source.name,
                news.url,
                news.urlToImage,
            )
            addArticle(article)
        }
    }

    private fun addArticle(article: ArticleLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }

    fun updateArticle(article: ArticleLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateArticle(article)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun deleteAllCategory(category: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllCategory(category)
        }
    }

    fun updateBookmark(bookmark: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBookmark(bookmark, id)
        }
    }

    fun getNewsByCategory(category: String): LiveData<List<ArticleLocal>> =
        repository.getNewsByCategory(category)

    fun readBookmarks(): LiveData<List<ArticleLocal>> {
        return repository.readBookmark
    }
}