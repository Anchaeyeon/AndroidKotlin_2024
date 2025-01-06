package com.example.andtest03

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtest03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            var name: String = binding.edtName.text.toString()
            var tel: String = binding.edtTel.text.toString()
            var str = "이름: " + name + "\n전화: " + tel
            Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
        }
    }
}