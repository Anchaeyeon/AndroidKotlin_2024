package com.example.ch7_layout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn1.setOnClickListener {
            binding.tv1.visibility = View.VISIBLE;
            binding.tv2.visibility = View.INVISIBLE;
            binding.tv3.visibility= View.INVISIBLE;
        }
        binding.btn2.setOnClickListener {
            binding.tv2.visibility = View.VISIBLE;
            binding.tv1.visibility = View.INVISIBLE;
            binding.tv3.visibility= View.INVISIBLE;
        }
        binding.btn3.setOnClickListener {
            binding.tv3.visibility = View.VISIBLE;
            binding.tv1.visibility = View.INVISIBLE;
            binding.tv2.visibility= View.INVISIBLE;
        }
    }
}