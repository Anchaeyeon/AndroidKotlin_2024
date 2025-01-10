package com.example.afterschool1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.afterschool1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //기본 id, pw로 설정
    var user_id = "admin"
    var user_pw = "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            var id = binding.edId.text.toString()
            var pw = binding.edPass.text.toString()

            if (id=="" || pw=="")
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            else {
                if (id==user_id && pw==user_pw) {
                    var intent = Intent(this, Login::class.java)
                    intent.putExtra("uID", binding.edId.text.toString())
                    intent.putExtra("uPW", binding.edPass.text.toString())
                    startActivity(intent)
                }
                else
                    Toast.makeText(this, "아이디 & 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegister.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivityForResult(intent, 99)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                var id = data?.getStringExtra("uID")
                var pw = data?.getStringExtra("uPW")
                binding.edId.setText(id)
                user_id = id.toString()
                binding.edPass.setText(pw)
                user_pw = pw.toString()
            }
        }
    }
}