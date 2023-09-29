package com.example.naturalgasdetector

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.naturalgasdetector.NotificationPeriodic.Companion.PERIODIC_NOTIFICATION_ID

class MenuActivity : AppCompatActivity() {

    companion object{
        const val CHANNEL_ID = "myChannel"
        var requestCode = 1 //Permiso de notificaciones
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Permiso de notificaciones

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // El permiso no se ha concedido, solicítalo al usuario
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), requestCode)
        } else {
        }

        //Programar notificaciones cada 5 horas

        periodicNotification()

        //Creando canal de notificaciones

        createChannel()

        //Botones de la pantalla principal

        val btnManual = findViewById<TextView>(R.id.btnManual)
        btnManual.setOnClickListener {
            //Insertar el codgio para descargar el manual de uso
        }

        val btnLogo = findViewById<ImageButton>(R.id.btnLogo)
        btnLogo.setOnClickListener{
            entrarInfo()
        }

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener{
            Toast.makeText(this, "¡Ya estas en Inicio!", Toast.LENGTH_SHORT).show()
        }

        val btnDangerZ = findViewById<ImageButton>(R.id.btnDangerZ)
        btnDangerZ.setOnClickListener{
            entrarDangerZ()
        }

        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)
        btnInfo.setOnClickListener{
            entrarInfo()
        }

        val btnInfoAutor = findViewById<TextView>(R.id.btnInfoAutor)
        btnInfoAutor.setOnClickListener {
            entrarInfo()
        }

        val btnTextConect = findViewById<TextView>(R.id.btnTextConect)
        btnTextConect.setOnClickListener{
            entrarConexion()
        }



    }

    private fun entrarInfo() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun entrarConexion() {
        val Intent = Intent(this, ConexionActivity::class.java)
        startActivity(Intent)
    }

    private fun entrarDangerZ() {
        val intent = Intent(this, DangerActivity::class.java)
        startActivity(intent)
    }


    //Crear canal para notificaciones
    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "MySuperChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Canal para lanzar notificaciones"
            }

            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun periodicNotification() {
        val intent = Intent(applicationContext, NotificationPeriodic::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            PERIODIC_NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        val notificationManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val startTime = System.currentTimeMillis() + 10000
        notificationManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            startTime,
            10000,
            pendingIntent
        )
    }
}