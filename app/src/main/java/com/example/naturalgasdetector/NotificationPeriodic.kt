package com.example.naturalgasdetector

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationPeriodic : BroadcastReceiver() {
    companion object{
        const val PERIODIC_NOTIFICATION_ID = 2
    }
    override fun onReceive(context: Context, p1: Intent?) {
        createNotificationPeriodic(context)
    }

    private fun createNotificationPeriodic(context: Context) {

        val intentNotification = Intent(context, MenuActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //Es para no crear nuevas instancias de la app
        }

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intentNotification, flag)

        var notificationPeriodic = NotificationCompat.Builder(context, MenuActivity.CHANNEL_ID)
            .setSmallIcon(R.mipmap.logo_notification)
            .setContentTitle("¡Informe!")
            .setContentText("Informe periodico del hogar")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Por ahora todo esta tranquilo en la zona de riesgo, si hay algo fuera de lo normal te notificaremos."
                    )
            )
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(PERIODIC_NOTIFICATION_ID, notificationPeriodic)
    }

}