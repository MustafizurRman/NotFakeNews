package com.fizz.notfakenews.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Local")
data class ArticleLocal(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title: String?,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val category: String?,
    val bookmark:Boolean?,
    val url: String?,
    val urlToImage: String?
)