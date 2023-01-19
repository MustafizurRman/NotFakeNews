package com.fizz.notfakenews.model

import androidx.lifecycle.LiveData

class ArticleRepository(private val articleDao: ArticleDao) {
    val readAllArticle: LiveData<List<ArticleLocal>> = articleDao.readAllArticle()

    suspend fun addArticle(article:ArticleLocal){
        articleDao.insertArticle(article)
    }
}