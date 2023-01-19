package com.fizz.notfakenews.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: ArticleLocal)
    @Query("SELECT * FROM local")
    fun readAllArticle(): LiveData<List<ArticleLocal>>
    @Update
    suspend fun updateArticle(article: ArticleLocal)
    @Delete
    suspend fun deleteArticle(article: ArticleLocal)
    @Query("DELETE FROM local")
    fun deleteAll()

}