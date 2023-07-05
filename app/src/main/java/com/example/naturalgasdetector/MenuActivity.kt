package com.example.naturalgasdetector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogoConect = findViewById<ImageButton>(R.id.btnLogoConect)
        btnLogoConect.setOnClickListener{
            entrarInfo()
        }

        val btnTextConect = findViewById<TextView>(R.id.btnTextConect)
        btnTextConect.setOnClickListener{
            entrarConexion()
        }

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener{
            entrarHome()
        }

        val btnPanel = findViewById<ImageButton>(R.id.btnPanel)
        btnPanel.setOnClickListener{
            entrarPanel()
        }

        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)
        btnInfo.setOnClickListener{
            entrarInfo()
        }

        val btnInfoAutor = findViewById<TextView>(R.id.btnInfoAutor)
        btnInfoAutor.setOnClickListener {
        }
    }
    fun entrarInfo() {
        val intent = Intent(this, infoActivity::class.java)
        startActivity(intent)
    }

    fun entrarConexion() {
        val intent = Intent(this, conexionActivity::class.java)
        startActivity(intent)
    }

    fun entrarHome() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    fun entrarPanel() {
        val intent = Intent(this, panelActivity::class.java)
        startActivity(intent)
    }
}