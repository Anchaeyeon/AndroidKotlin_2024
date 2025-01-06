package com.example.andtest02

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtest02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ok.setOnClickListener {
            var str: String = "학년 선택 안 함"
            when (binding.rg.checkedRadioButtonId) {
                R.id.rb2 -> str = "2학년"
                R.id.rb3 -> str = "3학년"
            }
            Toast.makeText(applicationContext, str,Toast.LENGTH_SHORT).show()
        }
        binding.cancel.setOnClickListener {
            binding.rg.clearCheck()
        }
    }
}