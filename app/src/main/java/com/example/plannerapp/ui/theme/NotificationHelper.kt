package com.example.plannerapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    fun showNotification(title: String) {

        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                "task_channel",
                "Tasks",
                NotificationManager.IMPORTANCE_HIGH
            )

            manager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(
            context,
            "task_channel"
        )
            .setContentTitle("Reminder")
            .setContentText(title)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        manager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }
}