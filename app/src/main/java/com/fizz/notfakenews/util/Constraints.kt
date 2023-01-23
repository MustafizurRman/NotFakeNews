package com.fizz.notfakenews.util

import android.content.Context
import android.net.ConnectivityManager

class Constraints {

    companion object{
         const val BASE_URL = "https://newsapi.org/v2/"
         const val API_KEY = "6bb86012ffc54085aa50e6fb36c4da43"

        fun checkConnectivity(context: Context):Boolean{
            val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo=connectivityManager.activeNetworkInfo
            return  networkInfo!=null && networkInfo.isConnected
        }
/*
        const val CATEGORY_TAB_NUMBER = "category_tab_number"
        const val KEY_URL = "url"
        const val DEFAULT = "www.google.com"
        const val BASE_URL = "https://newsapi.org/v2/"
        //        const val API_KEY = "f21a416b742a4428a7c6ef898b2c1998"
        const val API_KEY = "6c234acd217141a5b7955326bf8b2804"
        const val ARTICLE = "article"
        const val FAVORITE = "favorite"
        const val CATEGORY = "category"*/
    }
}