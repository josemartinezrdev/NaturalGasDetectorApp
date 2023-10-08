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
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.naturalgasdetector.NotificationPeriodic.Companion.PERIODIC_NOTIFICATION_ID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MenuActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "myChannel"
        var requestCode = 1 //Permiso de notificaciones
        private lateinit var databaseNotificacion: DatabaseReference
        private lateinit var databasePeriodicas: DatabaseReference
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        databaseNotificacion = FirebaseDatabase.getInstance().getReference("Notificacion")
        databasePeriodicas = FirebaseDatabase.getInstance().getReference("Periodicas")

        val contadorGasRef = FirebaseDatabase.getInstance().getReference("Registros/Contador Gas")

        val contadorPerRef = FirebaseDatabase.getInstance().getReference("Registros/Contador Per")

        databaseNotificacion.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val notificacionNumber = dataSnapshot.getValue(String::class.java)
                    if (notificacionNumber != null && notificacionNumber == "1") {

                        contadorGasRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(contadorSnapshot: DataSnapshot) {
                                val contador = contadorSnapshot.getValue(Long::class.java) ?: 0
                                val actContador = contador + 1

                                contadorGasRef.setValue(actContador)

                                val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                                val hora = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                                val nuevoRegistroRef = FirebaseDatabase.getInstance()
                                    .getReference("Registros/Registros Gas/Registro$actContador")

                                nuevoRegistroRef.setValue("Fuga de gas - $fecha - $hora")

                                notificationGas()
                            }
                            override fun onCancelled(databaseError: DatabaseError) {}
                        })
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        databasePeriodicas.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val periodicNumber = dataSnapshot.getValue(String::class.java)
                    if (periodicNumber == "1") {
                        contadorPerRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(contadorSnapshot: DataSnapshot) {
                                val contador = contadorSnapshot.getValue(Long::class.java) ?: 0
                                val actContador = contador + 1

                                contadorPerRef.setValue(actContador)

                                val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                                val hora = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                                val nuevoRegistroRef = FirebaseDatabase.getInstance()
                                    .getReference("Registros/Registros Periodicos/Registro$actContador")

                                nuevoRegistroRef.setValue("Todo en orden - $fecha - $hora")

                                databasePeriodicas.setValue("0")
                            }

                            override fun onCancelled(error: DatabaseError) {}
                        })
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        //Permiso para enviar notificaciones

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                requestCode
            )
        }

        //Programar notificaciones cada 5 horas

        periodicNotification()


        //Creando canal de notificaciones

        createChannel()


        //Botones de la pantalla principal

        val btnManual = findViewById<TextView>(R.id.btnManual)
        btnManual.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ngd-657-oficial.netlify.app/html/home"))
            startActivity(intent)

        }

        val btnLogo = findViewById<ImageButton>(R.id.btnLogo)
        btnLogo.setOnClickListener {
            entrarInfo()
        }

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener {
            Toast.makeText(this, "¡Ya estas en Inicio!", Toast.LENGTH_SHORT).show()
        }

        val btnDangerZ = findViewById<ImageButton>(R.id.btnDangerZ)
        btnDangerZ.setOnClickListener {
            entrarDangerZ()
        }

        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)
        btnInfo.setOnClickListener {
            entrarInfo()
        }

        val btnInfoAutor = findViewById<TextView>(R.id.btnInfoAutor)
        btnInfoAutor.setOnClickListener {
            entrarInfo()
        }

        val btnTextConnect = findViewById<TextView>(R.id.btnTextConnect)
        btnTextConnect.setOnClickListener {
            entrarConexión()
        }
    }

    private fun entrarInfo() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun entrarConexión() {
        val intent = Intent(this, ConexionActivity::class.java)
        startActivity(intent)
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

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
        val startTime = System.currentTimeMillis() + 3 * 60 * 60 * 1000
        notificationManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            startTime,
            3 * 60 * 60 * 1000,
            pendingIntent
        )
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun notificationGas() {
        val intent = Intent(applicationContext, NotificationGas::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NotificationGas.GAS_NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        notificationManager.setExact(AlarmManager.RTC_WAKEUP,
            (Calendar.getInstance().timeInMillis + 1), pendingIntent)
    }
}
