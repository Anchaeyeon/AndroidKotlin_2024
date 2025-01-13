package com.example.andtest3

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andtest3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val helper = MyDBHelper(this, "userDB", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<User>()
        var adapter = MyRecyclerAdapter(datas)
        datas.addAll(helper.selectUser())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.btnInit.setOnClickListener(View.OnClickListener{
            helper.deleteAll() //모든 레코드 삭제
            adapter.datas.clear()
            adapter.notifyDataSetChanged()
        })

        binding.btnAdd.setOnClickListener {
            var name = binding.edtName.getText().toString().trim()
            var tel = binding.edtTel.getText().toString().trim()
            if (name=="" || tel=="") {
                Toast.makeText(applicationContext, "이름과 전화번호를 모두 입력해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val mem = User(null, name, tel)
            helper.insertUser(mem)
            adapter.datas.clear()
            adapter.datas.addAll(helper.selectUser())
            adapter.notifyDataSetChanged()
            binding.edtName.setText("")
            binding.edtTel.setText("")
        }

        binding.btnSelect.setOnClickListener {
            adapter.datas.clear()
            adapter.datas.addAll(helper.selectUser())
            adapter.notifyDataSetChanged()
        }

        adapter.itemClick = object : MyRecyclerAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                var dialogView = View.inflate(this@MainActivity, R.layout.dlg, null)
                var dlg = AlertDialog.Builder(this@MainActivity)
                var name = dialogView.findViewById<TextView>(R.id.dlgName)
                var tel = dialogView.findViewById<TextView>(R.id.dlgTel)
                name.text = adapter.datas[position].name
                tel.text = adapter.datas[position].tel
                var num = adapter.datas[position].num
                dlg.setTitle("확인")
                dlg.setView(dialogView)
                dlg.setNegativeButton("닫기", null)
                dlg.setPositiveButton("삭제") { dialog, whith ->
                    if (num != null) { //레코드 삭제
                        helper.deleteUser(num)
                        adapter.datas.clear()
                        adapter.datas.addAll(helper.selectUser())
                        adapter.notifyDataSetChanged()
                    }
                }
                dlg.show()
            }
        }
    }
}