package com.example.andtest02

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_test4)

        var chk = findViewById<CheckBox>(R.id.chk)
        var linear = findViewById<LinearLayout>(R.id.linear)
        var rg = findViewById<RadioGroup>(R.id.rg)
        var btn = findViewById<Button>(R.id.btn)
        var imgPet = findViewById<ImageView>(R.id.imgPet)

        chk.setOnCheckedChangeListener { compoundButton, b ->
            if (chk.isChecked == true) linear.visibility = android.view.View.VISIBLE
            else {
                linear.visibility = android.view.View.INVISIBLE
                rg.clearCheck() //체크된 게 있으면 지우기
                chk.isChecked = false
                imgPet.setImageDrawable(null) //이미지를 지우기
            }
        }

        btn.setOnClickListener {
            when(rg.checkedRadioButtonId) {
                R.id.rdoDog -> imgPet.setImageResource(R.drawable.dog)
                //이미지 소스를 R.drawable.dog로 세팅하기
                R.id.rdoCat -> imgPet.setImageResource(R.drawable.cat)
                //이미지 소스를 R.drawable.cat으로 세팅하기
                R.id.rdoRabbit -> imgPet.setImageResource(R.drawable.rabbit)
                //이미지 소스를 R.drawable.rabbit으로 세팅하기
                else -> Toast.makeText(applicationContext, "동물 먼저 선택하세요", Toast.LENGTH_SHORT).show()
            }
        }
        /*
        btnOK.setOnClickListener{
            var name = edName.text
            Toast.makeText(applicationContext, "입력한 이름 : "+name, Toast.LENGTH_SHORT).show()
        }*/
    }
}