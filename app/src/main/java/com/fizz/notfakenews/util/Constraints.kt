package com.fizz.notfakenews.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ParseException
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

class Constraints {

    companion object{
         const val BASE_URL = "https://newsapi.org/v2/"
//         const val API_KEY = "6bb86012ffc54085aa50e6fb36c4da43 "
//
//         0027b74f478e421f969852269762888b  ed09f40d0f5e4a06b042467f05e5b0e4
        const val API_KEY="939b414905ac461db3673c7161e72285"

        @JvmField
        val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
            "Verbose WorkManager Notifications"
        const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications whenever work starts"

        @JvmField
        val NOTIFICATION_TITLE: CharSequence = "Work Manager Demo"
        const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
        const val NOTIFICATION_ID = 1

        fun checkConnectivity(context: Context):Boolean{
            val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo=connectivityManager.activeNetworkInfo
            return  networkInfo!=null && networkInfo.isConnected
        }

        fun DateToTimeFormat(oldstringDate: String?): String? {
            val p = PrettyTime(Locale(getCountry()))
            var isTime: String? = null
            try {
                val sdf = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.ENGLISH
                )
                val date: Date? = oldstringDate?.let { sdf.parse(it) }
                isTime = p.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return isTime
        }

        fun getCountry(): String {
            val locale: Locale = Locale.getDefault()
            val country: String = java.lang.String.valueOf(locale.getCountry())
            return country.lowercase(Locale.getDefault())
        }
    }
}