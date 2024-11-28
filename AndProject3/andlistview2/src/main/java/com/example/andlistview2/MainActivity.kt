package com.example.andlistview2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andlistview2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var midList = ArrayList<String>()
        var adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, midList)
        binding.listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        binding.listView1.adapter = adapter
        binding.btnAdd.setOnClickListener {
            midList.add(binding.edtItem.text.toString())
            adapter.notifyDataSetChanged()
            binding.edtItem.setText("")
            binding.edtItem.setHint("구입할 상품을 입력하세요.")
        }
        binding.btnDelete.setOnClickListener {
            val pos = binding.listView1.checkedItemPosition
            if (pos != ListView.INVALID_POSITION) {
                midList.removeAt(pos)
                binding.listView1.clearChoices()
                adapter.notifyDataSetChanged()
            }
        }
        binding.listView1.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, "선택한 항목명 : " + midList.get(position), Toast.LENGTH_SHORT).show()
        }
    }
}