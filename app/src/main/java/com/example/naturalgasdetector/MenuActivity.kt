package com.example.naturalgasdetector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogoConect = findViewById<ImageButton>(R.id.btnLogoConect)
        btnLogoConect.setOnClickListener{
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
}