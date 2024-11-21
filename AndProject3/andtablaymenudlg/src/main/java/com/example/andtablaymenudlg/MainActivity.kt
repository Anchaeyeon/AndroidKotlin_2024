package com.example.andtablaymenudlg

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtablaymenudlg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //생성
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater //액션바에 메뉴를 넣는 곳이 래퍼런스 가져오기
        mInflater.inflate(R.menu.option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemLogin -> {
                var dlg : AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("로그인")
                dlg.setIcon(R.drawable.login)
                var dlgView: View = View.inflate(this, R.layout.dialog, null)
                dlg.setView(dlgView)
                dlg.setPositiveButton("확인") { dialog, which ->
                    var id = dlgView.findViewById<EditText>(R.id.edtID).text.toString() //아이디 저장
                    var pw = dlgView.findViewById<EditText>(R.id.edtPW).text.toString() //비밀번호 저장
                    Toast.makeText(applicationContext, "아이디 : " + id + "\n비밀번호 : " + pw, Toast.LENGTH_SHORT).show()
                }
                dlg.setNegativeButton("취소", null)
                dlg.show()
            }
            R.id.itemLogout -> {
                var dlg : AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("로그아웃")
                dlg.setIcon(R.drawable.logout)
                dlg.setMessage("로그아웃 하시겠습니까?")
                dlg.setPositiveButton("확인", null)
                dlg.show()
            }
        }
        return true
    }
}