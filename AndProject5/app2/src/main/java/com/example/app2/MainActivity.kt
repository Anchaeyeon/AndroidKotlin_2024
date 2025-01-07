package com.example.app2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            var name = binding.edName.text.toString()
            var tel = binding.edTel.text.toString()
            if (name=="" || tel=="")
                Toast.makeText(application, "이름 & 전화번호 모두 입력하세요.", Toast.LENGTH_SHORT).show()
            else {
                var gender = ""
                if (binding.rdGroup.checkedRadioButtonId == R.id.rdMan)
                    gender = "남자"
                else
                    gender = "여자"
                var str = "이 름: " + name + "\n성 별: " + gender + "\n전화번호" + tel + "\n"
                val dlg = AlertDialog.Builder(this@MainActivity)
                dlg.setMessage(str)
                dlg.setTitle(binding.btnCheck.text.toString())
                dlg.setNeutralButton("취소", null)
                dlg.setPositiveButton("확인") { dialog, which ->
                    Toast.makeText(application, "확인완료!", Toast.LENGTH_SHORT).show()
                }
                dlg.show()
            }
        }
    }

    // 옵션 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater
        mInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemExit -> finish()
            R.id.itemClear -> {
                binding.edName.setText("")
                binding.edTel.setText("")
                binding.rdGroup.check(R.id.rdMan)
            }
        }
        return true
    }

    fun funChk(v: View) {
        when (v.id) {
            R.id.btnId -> binding.btnCheck.text = "ID 확인"
            R.id.btnPw -> binding.btnCheck.text = "Password 확인"
        }
    }
}