package com.example.andlistview1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andlistview1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mid = arrayOf("히어로즈", "24시", "로스트", "로스트룸", "스몰빌", "탐정몽크",
            "빅뱅이론", "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추럴", "브이")
        var adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, mid)
        //생성한 ArrayAdapter를 리스트뷰에 적용
        //binding.listView1.adapter = adapter

        //한 개만 고르기 가능하게
        //binding.listView1.choiceMode = ListView.CHOICE_MODE_SINGLE

        binding.listView1.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, mid[position], Toast.LENGTH_SHORT).show()
        }
    }
}