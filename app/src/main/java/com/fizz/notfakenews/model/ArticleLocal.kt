package com.fizz.notfakenews.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Local",indices = [Index(value = ["url"], unique = true)])
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
):Parcelable