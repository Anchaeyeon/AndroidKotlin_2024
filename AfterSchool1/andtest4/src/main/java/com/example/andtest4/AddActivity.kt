package com.example.andtest4

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.andtest4.databinding.ActivityAddBinding
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding
    val calendar: Calendar = Calendar.getInstance()
    val myDB: MyDBHelper = MyDBHelper(this)
    var mode: Int = 0 //수정모드 삭제모드 체크 변수

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todo = intent.getSerializableExtra("todo")
        if (todo != null) { //받은 todo가 있을 경우 수정모드로 세팅
            todo as Todo
            binding.edtTitle.setText(todo.title)
            binding.edtMemo.setText(todo.memo)
            var date = todo.writeDate //Long 타입의 date를 캘린더로 세팅
            val localDate = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate()
            calendar.set(Calendar.YEAR, localDate.year)
            calendar.set(Calendar.MONTH, localDate.monthValue-1)
            calendar.set(Calendar.DAY_OF_YEAR, localDate.dayOfMonth)
            binding.calDate.date = date
            mode = 1 //수정모드
        }

        binding.calDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_YEAR, dayOfMonth)
        }

        binding.fabConfirm.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val memo = binding.edtMemo.text.toString()
            val writedate = calendar.timeInMillis
            val todo = Todo(title, memo, writedate)
            if (mode==0)
                myDB.addTodo(todo)
            else if (mode==1)
                myDB.updateTodo(todo)
            Toast.makeText(this, "할 일 저장 성공", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.fabDel.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val memo = binding.edtMemo.text.toString()
            val writedate = calendar.timeInMillis
            val todo = Todo(title, memo, writedate)
            myDB.deleteTodo(todo)
            Toast.makeText(this, "할 일 삭제 성공", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //맨 위로 오픈(startActivity)
            startActivity(intent)
        }
    }
}