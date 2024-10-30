package com.example.a3010

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExercise1: Button = findViewById(R.id.btnExercise1)
        val btnExercise2: Button = findViewById(R.id.btnExercise2)
        val btnExercise3: Button = findViewById(R.id.btnExercise3)

        btnExercise1.setOnClickListener {
            val intent = Intent(this, Bai1Activity::class.java)
            startActivity(intent)
        }

        btnExercise2.setOnClickListener {
            val intent = Intent(this, Bai2Activity::class.java)
            startActivity(intent)
        }

        btnExercise3.setOnClickListener {
            val intent = Intent(this, Bai3Activity::class.java)
            startActivity(intent)
        }
    }
}

