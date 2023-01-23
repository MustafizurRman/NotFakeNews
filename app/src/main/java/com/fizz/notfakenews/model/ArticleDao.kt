package com.fizz.notfakenews.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: ArticleLocal)

    @Query("SELECT * FROM local")
    fun readAllArticle(): LiveData<List<ArticleLocal>>

    @Query("SELECT * FROM local WHERE bookmark= true")
    fun readBookmarked(): LiveData<List<ArticleLocal>>

    @Update
    suspend fun updateArticle(article: ArticleLocal)

    @Query("DELETE FROM local")
    suspend fun deleteAll()

    @Query("UPDATE local SET bookmark = :bookmark WHERE id =:id")
    suspend fun updateBookmark(bookmark: Boolean?, id: Int)

    @Query("SELECT * FROM local WHERE category= :category")
    fun newsByCategory(category: String): LiveData<List<ArticleLocal>>

    @Query("SELECT (SELECT COUNT(*) FROM local) == 0")
    fun isEmpty(): Boolean
}