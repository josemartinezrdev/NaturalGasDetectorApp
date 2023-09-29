package com.example.naturalgasdetector

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationWifi : BroadcastReceiver() {
    companion object{
        const val WIFI_NOTIFICATION_ID = 1
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

        var notificationWifi = NotificationCompat.Builder(context, MenuActivity.CHANNEL_ID)
            .setSmallIcon(R.mipmap.logo_notification)
            .setContentTitle("¡Conexión Exitosa!")
            .setContentText("El sistema se conectó con exito a WiFi.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Acabas de conectar el sistema a WiFi y esta listo para usar, toca el boton ON en DangerZone para verificar la conexión "
                    )
            )
            .setContentIntent(pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(WIFI_NOTIFICATION_ID, notificationWifi)
    }
}