package com.example.and_cal_table

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.and_cal_table.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numClick(v: View) {
        var btn : Button = v as Button //다운캐스팅
        if (binding.edtX.isFocused) {
            binding.edtX.setText(binding.edtX.text.toString() + btn.text.toString())
        }
        else if (binding.edtY.isFocused) {
            binding.edtY.setText(binding.edtY.text.toString() + btn.text.toString())
        }
        else {
            Toast.makeText(applicationContext, "먼저 EditText를 선택하세요.", Toast.LENGTH_SHORT).show()
        }
    }
    fun btnClick(v: View) {
        var x = binding.edtX.text.toString().toInt()
        var y = binding.edtY.text.toString().toInt()
        if (v.getId() == R.id.btnAdd)
            binding.txtResult.text = "계산결과 : " + (x+y).toString()
        else if (v.getId() == R.id.btnSub)
            binding.txtResult.text = "계산결과 : " + (x-y).toString()
        else
            binding.txtResult.text = "계산결과 : " + (x*y).toString()
    }
}