package com.fizz.notfakenews.model

data class NotNews(
    val articles: List<Article>,
    val status: String?,
    val totalResults: Int?
)