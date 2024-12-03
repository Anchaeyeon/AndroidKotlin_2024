package com.example.andrecytest1

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andrecytest1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var poster = arrayOf<Int>(R.drawable.mov01, R.drawable.mov02,   R.drawable.mov03, R.drawable.mov04,
        R.drawable.mov05,R.drawable.mov06, R.drawable.mov07,R.drawable.mov08, R.drawable.mov09, R.drawable.mov10 )
    var title = arrayOf("토이스토리4", "호빗3", "제이슨 본", "반지의 제왕 3", "정직한 후보", "나쁜 녀석들",
        "겨울왕국 2", "알라딘", "극한직업", "스파이더맨" )
    var genre = arrayOf(  "DRAMA",  "DRAMA",  "SRILLER", "DRAMA","DRAMA", "DRAMA",
        "SRILLER",  "DRAMA", "SRILLER","DRAMA")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<Movie>()
        for (i in 0 until poster.size) { //사용할 데이터 생성
            var m = Movie(ContextCompat.getDrawable(this, poster[i])!!, title[i], genre[i])
            datas.add(m)
        }
        val adapter = MovieAdapter(datas)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.itemClick = object : MovieAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                var title: String = datas[position].title
                Toast.makeText(this@MainActivity, "$title 선택!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}