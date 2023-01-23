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

private const val TAG = "OverViewModel"

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val _newsData = MutableLiveData<List<Article>>()
    val newsData: LiveData<List<Article>> = _newsData

    private val _topNews=MutableLiveData<List<Article>>()
    val topNews: LiveData<List<Article>> = _topNews

    private val _sports=MutableLiveData<List<Article>>()
    val sports: LiveData<List<Article>> = _sports

    val readAllNews: LiveData<List<ArticleLocal>>
    val readSports: LiveData<List<ArticleLocal>>
    val repository: ArticleRepository

    val categories = listOf("sports","business","entertainment","general","health","science","technology")

    init {
        val dao = ArticleDatabase.getDatabase(application).articleDao()
        repository = ArticleRepository(dao)
        readAllNews=repository.readAllArticle
        readSports=repository.readSports
        getNews()
    }

    fun getNews(){
        viewModelScope.launch {
            for(category in categories){
                _newsData.value = NewsApi.getAllNews(category).articles
                Log.d("9999999", "API fetch successful")
                try{
                    if(_newsData.value!!.isNotEmpty()){
                        viewModelScope.launch(Dispatchers.IO) {
                            newsData.value?.let { insertLocal(category, it) }
                        }
                    }
                }catch (e: Exception){
                    Log.d("ALLLLLLL","Data Ashtese nahhhhhh")
                }
            }
        }
    }

    fun insertLocal( category: String, newsList: List<Article>){
        for(news in newsList){
            val article = ArticleLocal(
                0,
                news.title,
                news.author,
                news.content,
                news.description,
                news.publishedAt,
                category,
                false,
                news.url,
                news.urlToImage,
            )
            addArticle(article)
        }
    }

    fun addArticle(article:ArticleLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }
    fun updateArticle(article: ArticleLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateArticle(article)
        }
    }

    fun deleteArticle(article: ArticleLocal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteArticle(article)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun updateBookmark(bookmark:Boolean,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBookmark(bookmark,id)
        }
    }

}