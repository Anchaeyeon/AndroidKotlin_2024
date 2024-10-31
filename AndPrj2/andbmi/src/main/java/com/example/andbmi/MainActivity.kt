package com.example.andbmi

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andbmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancel.setOnClickListener {
            binding.edtHeight.setText("")
            binding.edtWeight.setText("")
            binding.txt.text = ""
            binding.img.setImageResource(0)
        }
        binding.btnOK.setOnClickListener {
            var strW = binding.edtWeight.text.toString()
            var strH = binding.edtHeight.text.toString()
            if (strH?.trim().equals("") || strW?.trim().equals("")) { //입력을 하지 않았을 경우
                Toast.makeText(applicationContext, "몸무게와 키를 입력하세요.\n공백은 안 됩니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                var weight = strW.toDouble()
                var height = strH.toDouble()
                var bmi = weight/(height/100*height/100)
                //Math.pow(height/100, 2.0)도 가능
                when {
                    bmi >= 35.0 -> binding.txt.text = "고도 비만"
                    bmi >= 30.0 -> binding.txt.text = "2단계 비만"
                    bmi >= 25.0 -> binding.txt.text = "1단계 비만"
                    bmi >= 23.0 -> binding.txt.text = "과체중"
                    bmi >= 18.5 -> binding.txt.text = "정상"
                    else -> binding.txt.text = "저체중"
                }
                when {
                    bmi >= 23 -> binding.img.setImageResource(R.drawable.not_smile)
                    bmi >= 18.5 -> binding.img.setImageResource(R.drawable.smile)
                    else -> binding.img.setImageResource(R.drawable.so_smile)
                }
                binding.txt.visibility = View.VISIBLE
                binding.img.visibility = View.VISIBLE
            }
        }
    }
}