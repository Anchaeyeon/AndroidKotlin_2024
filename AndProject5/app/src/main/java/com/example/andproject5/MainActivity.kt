package com.example.andproject5

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andproject5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    var total = 10000
    lateinit var btns : Array<Button>
    lateinit var lays : Array<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btns = arrayOf<Button>(binding.btnData, binding.btnInput, binding.btnOutput)
        lays = arrayOf<LinearLayout>(binding.layData, binding.layInput, binding.layOutput)

        for (i in 0 until btns.size) {
            btns[i].setOnClickListener { outView(i) }
        }
        // 입금
        binding.btnInputOk.setOnClickListener {
            val n: Int = binding.edtInput.text.toString().toInt()
            total += n
            outPut(n)
        }
        // 출금
        binding.btnOutputOk.setOnClickListener {
            val n: Int = binding.edtOutput.text.toString().toInt()
            total -= n
            outPut(n)
        }
    }
    fun outPut(n: Int) {
        binding.txtData.text = "잔액: $total".toString() + "원"
        Toast.makeText(applicationContext, "${n}원 입금 잔액은 $total".toString() + "입니다.", Toast.LENGTH_SHORT).show()
        binding.edtInput.setText("0")
    }
    fun outView(k: Int) {
        for (i in btns.indices) {
            btns[i].setBackgroundColor(Color.rgb(225, 221, 221))
            //btns[i].setBackgroundColor(Color.LTGRAY)
            lays[i].setVisibility(View.INVISIBLE)
        }
        btns[k].setBackgroundColor(Color.rgb(244, 244, 127))
        lays[k].setVisibility(View.VISIBLE)
    }
}