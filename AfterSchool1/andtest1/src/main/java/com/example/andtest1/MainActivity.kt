package com.example.andtest1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andtest1.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val result = it.data!!.getStringExtra("result")
            if (result!=null && result!!.toString()!="cancel") {
                datas?.add(result)
                adapter.notifyDataSetChanged()
            }
        }
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent) //양방향
        }
        datas = savedInstanceState?.let {
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let { mutableListOf<String>() }  //let: null 체크

        adapter = MyAdapter(datas) // 어댑터에 데이터 세팅
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this) // 리사이클러뷰 세팅 (레이아웃 설정)
        binding.mainRecyclerView.adapter = adapter // 리사이클러뷰에 어댑터 연결

        // 툴바 세팅, ActionBarDrawerToggle로 네비게이션 아이콘 연결
        setSupportActionBar(binding.toolbar)

        // ActionBarDrawerToggle로 네비게이션 아이콘 연결
        val toggle = ActionBarDrawerToggle(
            this, binding.drawer, binding.toolbar,
            R.string.navi_drawer_open, R.string.navi_drawer_close )
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }
}