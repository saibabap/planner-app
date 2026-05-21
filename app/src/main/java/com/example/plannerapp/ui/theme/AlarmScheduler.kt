package com.example.plannerapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class AlarmScheduler(
    private val context: Context
) {

    fun scheduleAlarm(
        timeInMillis: Long
    ) {

        val intent =
            Intent(
                context,
                AlarmReceiver::class.java
            )

        val pendingIntent =
            PendingIntent.getBroadcast(

                context,

                0,

                intent,

                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        val alarmManager =
            context.getSystemService(
                Context.ALARM_SERVICE
            ) as AlarmManager

        alarmManager.setExactAndAllowWhileIdle(

            AlarmManager.RTC_WAKEUP,

            timeInMillis,

            pendingIntent
        )
    }
}