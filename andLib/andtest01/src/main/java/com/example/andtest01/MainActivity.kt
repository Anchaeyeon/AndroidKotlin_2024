package com.example.andtest01

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var txtName = findViewById<TextView>(R.id.txtName)
        var btnName = findViewById<Button>(R.id.btnName)

        btnName.setOnClickListener{
            txtName.text="미림"
        }
    }
}