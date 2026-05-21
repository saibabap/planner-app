package com.example.plannerapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        val channelId =
            "planner_channel"

        val manager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        val channel =
            NotificationChannel(

                channelId,

                "Planner Notifications",

                NotificationManager.IMPORTANCE_HIGH
            )

        manager.createNotificationChannel(
            channel
        )

        val notification =
            NotificationCompat.Builder(
                context,
                channelId
            )

                .setContentTitle(
                    "Task Reminder"
                )

                .setContentText(
                    "Your task time reached"
                )

                .setSmallIcon(
                    android.R.drawable.ic_dialog_info
                )

                .setAutoCancel(true)

                .build()

        manager.notify(
            1,
            notification
        )
    }
}