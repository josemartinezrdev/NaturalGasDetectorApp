package com.example.naturalgasdetector

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ingenieriajhr.blujhr.BluJhr


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
        val LinearLAY = findViewById<LinearLayout>(R.id.LinearLAY)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val campSSID = findViewById<EditText>(R.id.campSSID)
        val campPASS = findViewById<EditText>(R.id.campPASS)
        val consola = findViewById<TextView>(R.id.consola)


        listBT.setOnItemClickListener { adapterView, view, i, l ->
            if (devicesBluetooth.isNotEmpty()){
                blue.connect(devicesBluetooth[i])
                blue.setDataLoadFinishedListener(object:BluJhr.ConnectedBluetooth{
                    override fun onConnectState(state: BluJhr.Connected) {
                        when(state){

                            BluJhr.Connected.True->{
                                Toast.makeText(applicationContext,"Dispositivo Conectado",Toast.LENGTH_SHORT).show()
                                listBT.visibility = View.GONE
                                LinearLAY.visibility = View.VISIBLE
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
                                LinearLAY.visibility = View.GONE
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
                esp32Ip = consola.text.toString()+rx
            }
        })
    }

    //Permisos para android 12

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (blue.checkPermissions(requestCode,grantResults)){
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
            blue.initializeBluetooth()
        }else{
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
                blue.initializeBluetooth()
            }else{
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

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

    override fun onBackPressed() {
        // Llamar a la función super.onBackPressed() para mantener el comportamiento predeterminado
        super.onBackPressed()

        // Lanzar la actividad DangerActivity y enviar el valor esp32Ip
        val intent = Intent(this, DangerActivity::class.java)
        intent.putExtra("clave", esp32Ip)
        startActivity(intent)
    }

}
