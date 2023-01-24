package com.fizz.notfakenews.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fizz.notfakenews.R
import com.fizz.notfakenews.util.Constraints.Companion.CHANNEL_ID
import com.fizz.notfakenews.util.Constraints.Companion.NOTIFICATION_ID
import com.fizz.notfakenews.util.Constraints.Companion.NOTIFICATION_TITLE
import com.fizz.notfakenews.util.Constraints.Companion.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.fizz.notfakenews.util.Constraints.Companion.VERBOSE_NOTIFICATION_CHANNEL_NAME

@SuppressLint("MissingPermission")
fun makeStatusNotification(context: Context, message: String) {

    // Make a channel if necessary
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
        Toast.makeText(context, "Working mf", Toast.LENGTH_SHORT).show()
    }

    // Create the notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(NOTIFICATION_TITLE)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}
