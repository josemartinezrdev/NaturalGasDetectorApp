package com.example.naturalgasdetector

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ingenieriajhr.blujhr.BluJhr
import java.util.Calendar

class ConexionActivity : AppCompatActivity() {

    var permisosOnBluetooth = false
    var requiredPermissions = listOf<String>()
    var devicesBluetooth = ArrayList<String>()

    lateinit var blue:BluJhr

    var esp32Ip: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexion)

        blue = BluJhr(this)
        blue.onBluetooth()

        val listBT = findViewById<ListView>(R.id.listBT)
        val LAYOUT_RED = findViewById<LinearLayout>(R.id.LAYOUT_RED)
        val LAYOUT_MENU = findViewById<LinearLayout>(R.id.LAYOUT_MENU)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val campSSID = findViewById<EditText>(R.id.campSSID)
        val campPASS = findViewById<EditText>(R.id.campPASS)

        //Menú

        var valor = intent.getStringExtra("valor")

        //Botones del menú

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener {
            entrarHome()
        }

        val btnDangerZ = findViewById<ImageButton>(R.id.btnDangerZ)
        btnDangerZ.setOnClickListener {
            entrarDangerZ()
        }

        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)
        btnInfo.setOnClickListener {
            entrarInfo()
        }

        //val consola = findViewById<TextView>(R.id.consola)

        listBT.setOnItemClickListener { adapterView, view, i, l ->
            if (devicesBluetooth.isNotEmpty()){
                blue.connect(devicesBluetooth[i])
                blue.setDataLoadFinishedListener(object:BluJhr.ConnectedBluetooth{
                    override fun onConnectState(state: BluJhr.Connected) {
                        when(state){

                            BluJhr.Connected.True->{
                                Toast.makeText(applicationContext,"Dispositivo Conectado",Toast.LENGTH_SHORT).show()
                                listBT.visibility = View.GONE
                                LAYOUT_RED.visibility = View.VISIBLE
                                LAYOUT_MENU.visibility = View.VISIBLE
                                rxReceived()
                            }

                            BluJhr.Connected.Pending->{
                                Toast.makeText(applicationContext,"Conectando...",Toast.LENGTH_SHORT).show()

                            }

                            BluJhr.Connected.False->{
                                Toast.makeText(applicationContext,"Algo a salido mal...",Toast.LENGTH_SHORT).show()
                            }

                            BluJhr.Connected.Disconnect->{
                                Toast.makeText(applicationContext,"Dispositivo Desconectado",Toast.LENGTH_SHORT).show()
                                listBT.visibility = View.VISIBLE
                                LAYOUT_RED.visibility = View.GONE
                                LAYOUT_MENU.visibility = View.GONE
                            }

                        }
                    }
                })
            }
        }

        btnSend.setOnClickListener {

            val ssid = campSSID.text.toString()
            val password = campPASS.text.toString()
            val wifiData = "$ssid,$password"

            blue.bluTx(wifiData)
        }

        btnSend.setOnLongClickListener {
            blue.closeConnection()
            true
        }
    }

    private fun rxReceived() {
        blue.loadDateRx(object:BluJhr.ReceivedData{
            override fun rxDate(rx: String) {
                val consola = findViewById<TextView>(R.id.consola)
                consola.text = consola.text.toString()+rx
                esp32Ip = rx
                //Llamar a la notificacion del wifi

                notificationWifi()
            }
        })
    }

    //Permisos para android

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (blue.checkPermissions(requestCode,grantResults)){
            Toast.makeText(this, "Permisos otorgados con exito", Toast.LENGTH_SHORT).show()
            blue.initializeBluetooth()
        }else{
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
                blue.initializeBluetooth()
            }else{
                Toast.makeText(this, "Algo salio mal en los permisos", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //Listar dispositivos

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!blue.stateBluetoooth() && requestCode == 100){
            blue.initializeBluetooth()
        }else{
            if (requestCode == 100){
                devicesBluetooth = blue.deviceBluetooth()
                if (devicesBluetooth.isNotEmpty()){
                    val adapter = ArrayAdapter(this,R.layout.row_list,devicesBluetooth)
                    val listDeviceBluetooth = findViewById<ListView>(R.id.listBT)
                    listDeviceBluetooth.adapter = adapter
                }else{
                    Toast.makeText(this, "No tienes vinculados dispositivos", Toast.LENGTH_SHORT).show()
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun entrarHome() {
        val intentIP = Intent(this, MenuActivity::class.java)
        intentIP.putExtra("clave", esp32Ip)

        Toast.makeText(this, "IP: $esp32Ip", Toast.LENGTH_SHORT).show()

        startActivity(intentIP)
    }

    private fun entrarDangerZ() {
        val intentIP = Intent(this, DangerActivity::class.java)
        intentIP.putExtra("clave", esp32Ip)

        Toast.makeText(this, "IP: $esp32Ip", Toast.LENGTH_SHORT).show()

        startActivity(intentIP)
    }

    private fun entrarInfo() {
        val intentIP = Intent(this, InfoActivity::class.java)
        intentIP.putExtra("clave", esp32Ip)

        Toast.makeText(this, "IP: $esp32Ip", Toast.LENGTH_SHORT).show()

        startActivity(intentIP)
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun notificationWifi() {
        val intent = Intent(applicationContext, NotificationWifi::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NotificationWifi.WIFI_NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        notificationManager.setExact(AlarmManager.RTC_WAKEUP,
            (Calendar.getInstance().timeInMillis + 1), pendingIntent)
    }

}
