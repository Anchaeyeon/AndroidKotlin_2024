package com.example.afterschool1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.afterschool1.databinding.LoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        var uid = intent.getStringExtra("uID")
        var upass = intent.getStringExtra("uPW")

        binding.txtId.text = uid
        binding.txtPass.text = upass

        binding.btnExit.setOnClickListener { finish() }
    }
}