package com.example.andprj2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andprj2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            Toast.makeText(applicationContext, "안채연", Toast.LENGTH_SHORT).show()
        }

        binding.chk.setOnCheckedChangeListener { compoundButton, b ->
            if (binding.chk.isChecked) binding.btn.isEnabled = false
            else binding.btn.isEnabled = true
        }
    }
}