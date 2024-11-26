package com.example.andactivitytest2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andactivitytest2.databinding.ActivityMainBinding
import com.example.andactivitytest2.databinding.SecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding : SecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        var name = intent.getStringExtra("name")
        var age = intent.getIntExtra("age", 0)
        var tel = intent.getStringExtra("tel")
        var address = intent.getStringExtra("address")
        binding.txtResult.text = "이름 : " + name + "\n나이 : " + age + "\n전화 : " + tel + "\n주소 : " + address + "\n"

        binding.btnReturn.setOnClickListener { finish() }
    }
}