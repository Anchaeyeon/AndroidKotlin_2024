package com.example.andactivitytest2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andactivitytest2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInput.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", binding.edtName.text.toString())
            intent.putExtra("age", binding.edtAge.text.toString().toInt())
            intent.putExtra("tel", binding.edtTel.text.toString())
            intent.putExtra("address", binding.edtAddress.text.toString())
            startActivity(intent)
        }
    }
}