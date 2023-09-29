package com.example.naturalgasdetector

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class splashActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()

        }
}