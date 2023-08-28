package com.example.naturalgasdetector

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

private var ipAddress: String? = null

class DangerActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danger)

        //Recibiendo ip

        val VAL_IP = intent.getStringExtra("clave")

        //Codigo del menú

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        val btnDangerZ = findViewById<ImageButton>(R.id.btnDangerZ)
        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)

        btnHome.setOnClickListener {
            entrarHome()
        }

        btnDangerZ.setOnClickListener {
            Toast.makeText(this, "¡Ya estas en Danger Zone!", Toast.LENGTH_SHORT).show()
        }

        btnInfo.setOnClickListener {
            entrarInfo()
        }

        //Valores botones DangerZ

        val onSystem = findViewById<Button>(R.id.onSystem)
        val offSystem = findViewById<Button>(R.id.offSystem)

        var url:String

        onSystem.setOnClickListener {
            url = "http://$VAL_IP/onSystem"
            enviarRespuestaHttp(url)
            //Toast.makeText(this, url, Toast.LENGTH_SHORT).show()
        }
        offSystem.setOnClickListener {
            url = "http://$VAL_IP/offSystem"
            enviarRespuestaHttp(url)
            //Toast.makeText(this, url, Toast.LENGTH_SHORT).show()
        }

        val onGas = findViewById<Button>(R.id.onGas)
        val offGas = findViewById<Button>(R.id.offGas)

        onGas.setOnClickListener {}
        offGas.setOnClickListener {}

        val onLed = findViewById<Button>(R.id.onLed)
        val offLed = findViewById<Button>(R.id.offLed)

        onLed.setOnClickListener {}
        offLed.setOnClickListener {}

    }

    private fun entrarInfo() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun entrarHome() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    // Función para enviar una solicitud HTTP al ESP32
    private fun enviarRespuestaHttp(url: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val url = URL(url)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Manejar la respuesta del servidor si es necesario
                } else {
                    // Manejo de errores si la respuesta no es OK
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}