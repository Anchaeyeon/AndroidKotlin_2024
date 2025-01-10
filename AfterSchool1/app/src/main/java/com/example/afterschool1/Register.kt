package com.example.afterschool1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.afterschool1.databinding.LoginBinding
import com.example.afterschool1.databinding.RegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: RegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnter.setOnClickListener {
            var id = binding.edId.text.toString()
            var pw = binding.edPass.text.toString()
            if (id=="" || pw=="")
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show()
            else {
                var outIntent: Intent = Intent()
                outIntent.putExtra("uID", id)
                outIntent.putExtra("uPW", pw)
                setResult(RESULT_OK, outIntent)
                finish()
            }
        }
    }
}