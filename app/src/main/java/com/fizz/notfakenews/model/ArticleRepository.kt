package com.fizz.notfakenews.model

import androidx.lifecycle.LiveData

class ArticleRepository(private val articleDao: ArticleDao) {
    val readAllArticle: LiveData<List<ArticleLocal>> = articleDao.readAllArticle()
    val readBookmark:LiveData<List<ArticleLocal>> = articleDao.readBookmarked()

    suspend fun addArticle(article:ArticleLocal){
        articleDao.insertArticle(article)
    }

    suspend fun deleteAll(){
        articleDao.deleteAll()
    }

    suspend fun updateBookmark(bookmark:Boolean,id:Int){
        articleDao.updateBookmark(bookmark,id)
    }


    suspend fun updateArticle(article: ArticleLocal){
        articleDao.updateArticle(article)
    }

     fun getNewsByCategory(category: String): LiveData<List<ArticleLocal>>{
        return articleDao.newsByCategory(category)
    }

     fun dbIsEmpty():Boolean{
        return articleDao.isEmpty()
    }

}