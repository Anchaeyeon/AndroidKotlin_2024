package com.example.andtest4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andtest4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val helper = MyDBHelper(this)
    val datas = mutableListOf<Todo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = MyAdapter(datas)
        datas.addAll(helper.allTodo())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(applicationContext, AddActivity::class.java)
                val todo = datas.get(position)
                intent.putExtra("todo", todo)
                startActivity(intent)
            }
        }

        //툴바 세팅, ActionBarDrawerToggle로 홈버튼 아이콘 연결
        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this, binding.drawer, binding.toolbar,
            R.string.navi_drawer_open, R.string.navi_drawer_close )
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState() //홈버튼 표시
        binding.mainDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item1 -> {
                    Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.item2 -> {
                    Toast.makeText(this, "Question clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.item3 -> {
                    Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.item4 -> {
                    binding.drawer.closeDrawer(GravityCompat.START); true //내비게이션 닫기
                }
            }
            binding.drawer.closeDrawer(GravityCompat.START) //내비게이션 닫기
            true //true를 반환하면 클릭 이벤트가 처리됨
        }
    }
}