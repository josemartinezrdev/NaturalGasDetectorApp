package com.example.naturalgasdetector

import android.content.BroadcastReceiver
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationGas : BroadcastReceiver() {
    companion object{
        const val GAS_NOTIFICATION_ID = 3
    }

    override fun onReceive(context: Context, p1: Intent?) {
        createNotificationWifi(context)
    }
    private fun createNotificationWifi(context: Context) {

        val intentNotification = Intent().apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //Es para no crear nuevas instancias de la app
        }

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intentNotification, flag)

        var notificationGas = NotificationCompat.Builder(context, MenuActivity.CHANNEL_ID)
            .setSmallIcon(R.mipmap.logo_notification)
            .setContentTitle("¡ALERTA!")
            .setContentText("El sistema se detecto una fuga de gas natural.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("El sistema acaba de detectar una fuga de gas natural en la zona de riesgo, contacta con tu proveedor de gas para informar esta situación"
                    )
            )
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(GAS_NOTIFICATION_ID, notificationGas)
    }
}