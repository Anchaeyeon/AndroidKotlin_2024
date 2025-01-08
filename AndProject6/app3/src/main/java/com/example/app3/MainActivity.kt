package com.example.app3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private val itemList = mutableListOf<Item>()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //초기 데이터 설정
        for (i in 1..5) {
            itemList.add(Item("Item $i", false))
        }
        adapter = ItemAdapter(itemList) { position, isChecked ->
            itemList[position].isChecked = isChecked
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        //항목 추가 버튼 클릭 이벤트
        binding.addButton.setOnClickListener {
            val newItemName = binding.edtText.text.toString()
            if (newItemName.isNotEmpty()) {
                adapter.addItem(Item(newItemName, false))
                binding.edtText.text.clear()
            }
            else {
                Toast.makeText(this, "Enter a valid item name", Toast.LENGTH_SHORT).show()
            }
        }
        //선택된 항목 삭제 버튼 클릭 이벤트
        binding.removeButton.setOnClickListener {
            adapter.removeSelectedItems()
        }
    }
}