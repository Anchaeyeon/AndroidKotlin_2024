package com.example.app6

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var poster = arrayOf<Int>(
        R.drawable.mov01,
        R.drawable.mov02,
        R.drawable.mov03,
        R.drawable.mov04,
        R.drawable.mov05,
        R.drawable.mov06,
        R.drawable.mov07,
        R.drawable.mov08,
        R.drawable.mov09,
        R.drawable.mov10
    )
    var title = arrayOf(
        "토이스토리4", "호빗3", "제이슨 본", "반지의 제왕 3", "정직한 후보", "나쁜 녀석들",
        "겨울왕국 2", "알라딘", "극한직업", "스파이더맨"
    )
    var genre = arrayOf(
        "DRAMA", "DRAMA", "SRILLER", "DRAMA", "DRAMA", "DRAMA",
        "SRILLER", "DRAMA", "SRILLER", "DRAMA"
    )
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<Movie>()
        for (i in 0 until poster.size) {
            var m = Movie(ContextCompat.getDrawable(this, poster[i])!!, title[i], genre[i])
            datas.add(m)
        }
        val adapter = MovieAdapter(datas)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.itemClick = object : MovieAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val title: String = datas[position].title
                Toast.makeText(this@MainActivity, "$title 선택!", Toast.LENGTH_SHORT).show()
            }
        }
        //플로팅 버튼 이벤트 처리
        binding.fab.setOnClickListener {
            val dlgView = View.inflate(this@MainActivity, R.layout.dialog, null)
            val dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("영화 정보 입력")
            dlg.setIcon(R.mipmap.ic_launcher_round)
            dlg.setView(dlgView)
            dlg.setNegativeButton("취소", null)
            dlg.setNegativeButton("확인") { _, _ ->
                val edtTitle = dlgView.findViewById<EditText>(R.id.edtTitle)
                val edtGenre = dlgView.findViewById<EditText>(R.id.edtGenre)
                val movie = Movie(
                    ContextCompat.getDrawable(applicationContext, poster[0])!!,
                    edtTitle.text.toString(), edtGenre.text.toString()
                )
                datas.add(movie)
                adapter.notifyDataSetChanged() //리사이클러뷰 새로고침
            }
            dlg.show()
        }
        setSupportActionBar(binding.toolbar)

        // ActionBarDrawerToggle로 네비게이션 아이콘 연결
        val toggle = ActionBarDrawerToggle(
            this, binding.drawer, binding.toolbar,
            R.string.navi_drawer_open, R.string.navi_drawer_close
        )
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState() //네비게이션 아이콘 표시
        binding.mainDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item1 -> { Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show() }
                R.id.item2 -> { Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()}
                R.id.item4 -> { binding.drawer.closeDrawer(GravityCompat.START); true}
            }
            binding.drawer.closeDrawer(GravityCompat.START) //내비게이션 닫기
            true //true를 반환하면 클릭 이벤트가 처리됨
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_navigation -> {
                binding.drawer.openDrawer(GravityCompat.START) //NavigationView 열기
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}