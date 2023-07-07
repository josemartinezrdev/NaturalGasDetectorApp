package com.example.naturalgasdetector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class PanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)

        //Codigo del menú

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        val btnPanel = findViewById<ImageButton>(R.id.btnPanel)
        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)

        btnHome.setOnClickListener {
            entrarHome()
        }

        btnPanel.setOnClickListener {
            entrarPanel()
        }

        btnInfo.setOnClickListener {
            entrarInfo()
        }

        //Valores botones panel

        val onAlarm = findViewById<Button>(R.id.onAlarm)
        val offAlarm = findViewById<Button>(R.id.offAlarm)

        val onGas = findViewById<Button>(R.id.onGas)
        val offGas = findViewById<Button>(R.id.offGas)

        val onLed = findViewById<Button>(R.id.onLed)
        val offLed = findViewById<Button>(R.id.offLed)



    }

    private fun entrarInfo() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun entrarHome() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun entrarPanel() {
        val intent = Intent(this, PanelActivity::class.java)
        startActivity(intent)
    }

}