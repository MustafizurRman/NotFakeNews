package com.fizz.notfakenews.network

import com.fizz.notfakenews.model.NotNews
import com.fizz.notfakenews.util.Constraints
import com.fizz.notfakenews.util.Constraints.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=$API_KEY")
    suspend fun getTopNewsUS(): NotNews

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("category") category: String?,
        @Query("country") countryCode: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): NotNews

    @GET("top-headlines")
    suspend fun getByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = Constraints.API_KEY
    ):NotNews

    @GET("top-headlines")
    suspend fun getNewsByCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Constraints.API_KEY
    ):NotNews
}