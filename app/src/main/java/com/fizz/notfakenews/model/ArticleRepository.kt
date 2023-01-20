package com.fizz.notfakenews.model

import androidx.lifecycle.LiveData

class ArticleRepository(private val articleDao: ArticleDao) {
    val readAllArticle: LiveData<List<ArticleLocal>> = articleDao.readAllArticle()

    suspend fun addArticle(article:ArticleLocal){
        articleDao.insertArticle(article)
    }

    suspend fun deleteArticle(article: ArticleLocal){
        articleDao.deleteArticle(article)
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

}