package com.example.anddb_recy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anddb_recy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val helper = SqliteHelper(this, "memo", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RecyclerAdapter() //어댑터 생성
        adapter.helper = helper //어댑터에 사용할 데이터를 DB로 연결
        adapter.listData.addAll(helper.selectMemo()) //테이블의 memo select 해서 데이터 가져오기
        binding.recyclerMemo.adapter = adapter //리사이클러뷰에 연결
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this) //리사이클러뷰의 레이아웃 지정

        binding.buttonSave.setOnClickListener {
            if (binding.editMemo.text.toString().isNotEmpty()) {
                val memo = Memo(null, binding.editMemo.text.toString(), System.currentTimeMillis())
                helper.insertMemo(memo) //db에 insert(Memo 클래스 형태로)
                adapter.listData.clear() //리스트뷰 지우기
                adapter.listData.addAll(helper.selectMemo()) //새로 입력한 데이터까지 다시 select
                adapter.notifyDataSetChanged() //리사이클러뷰 새로고침
                binding.editMemo.setText("")
            }
        }
    }
}