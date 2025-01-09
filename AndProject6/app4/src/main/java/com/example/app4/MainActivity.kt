package com.example.app4

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var poster = arrayOf<Int>(R.drawable.mov01, R.drawable.mov02,   R.drawable.mov03, R.drawable.mov04,
        R.drawable.mov05,R.drawable.mov06, R.drawable.mov07,R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
        R.drawable.mov01, R.drawable.mov02,   R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,R.drawable.mov06,
        R.drawable.mov07,R.drawable.mov08, R.drawable.mov09, R.drawable.mov10 )
    var title = arrayOf("토이스토리4", "호빗3", "제이슨 본", "반지의 제왕 3", "정직한 후보", "나쁜 녀석들","겨울왕국 2", "알라딘", "극한직업", "스파이더맨",
        "토이스토리4", "호빗3", "제이슨 본", "반지의 제왕 3", "정직한 후보", "나쁜 녀석들","겨울왕국 2", "알라딘", "극한직업", "스파이더맨" )

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<Movie>()
        for (i in 0 until poster.size) {
            var m = Movie(ContextCompat.getDrawable(this, poster[i])!!, title[i])
            datas.add(m)
        }
        val adapter = MyGalleryAdapter(datas)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        adapter.itemClick = object : MyGalleryAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                binding.img.setImageDrawable(datas[position].image)
                Toast.makeText(this@MainActivity, datas[position].title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}