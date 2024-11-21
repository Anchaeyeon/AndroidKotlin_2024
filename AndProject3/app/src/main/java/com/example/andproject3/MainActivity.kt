package com.example.andproject3

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andproject3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            startFun()
        }
        binding.btnStop.setOnClickListener {
            stopFun()
        }
        binding.btnReset.setOnClickListener {
            restartFun()
        }
    }

    //메뉴 설정 (액션바에 들어감) --> 테마((theme)values 폴더에 있음) 설정에서 NoActionBar 이면 메뉴 오픈 불가

    //오버라이드 하기 (code -> Override Method)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater
        mInflater.inflate(R.menu.option, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemStart -> {startFun()}
            R.id.itemStop -> {stopFun()}
            R.id.itemRestart -> {restartFun()}
            R.id.itemExit -> {
                var dlg : AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("예약 제목 입력")
                dlg.setIcon(R.mipmap.ic_launcher_round)
                dlg.setMessage("종료 메뉴입니다.")
                dlg.setPositiveButton("확인", null)
                dlg.show()
            }
            R.id.itemDlg1 -> {
                var dlg : AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("좋아하는 분식은?")
                dlg.setIcon(R.mipmap.ic_launcher_round)
                var arr = arrayOf("떡볶이", "튀김", "어묵")
                dlg.setItems(arr) { dialog, which ->
                    Toast.makeText(applicationContext, arr[which], Toast.LENGTH_SHORT).show()
                }
                dlg.setPositiveButton("확인", null)
                dlg.show()
            }
            R.id.itemDlg2 -> {
                var dlg : AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                dlg.setTitle("로그인")
                dlg.setIcon(R.mipmap.ic_launcher_round)
                var dlgView: View = View.inflate(this, R.layout.dialog, null)
                dlg.setView(dlgView)
                dlg.setPositiveButton("확인", null)
                dlg.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun startFun():Unit {
        binding.chrono.base = SystemClock.elapsedRealtime() + pauseTime
        binding.chrono.start()
        // 스타트 버튼은 안 눌리게, 스탑&리셋 버튼은 눌리게
        binding.btnStart.isEnabled = false
        binding.btnStop.isEnabled = true
        binding.btnReset.isEnabled = true
        // 버튼 색 바꿔주기
        binding.btnStop.setBackgroundColor(Color.parseColor("#F35AC7"))
        binding.btnReset.setBackgroundColor(Color.parseColor("#F35AC7"))
        binding.btnStart.setBackgroundColor(Color.LTGRAY)
    }

    fun stopFun():Unit {
        pauseTime = binding.chrono.base - SystemClock.elapsedRealtime();
        binding.chrono.stop()

        binding.btnStart.isEnabled = true
        binding.btnStop.isEnabled = false
        binding.btnReset.isEnabled = true
        binding.btnStart.setBackgroundColor(Color.parseColor("#F35AC7"))
        binding.btnReset.setBackgroundColor(Color.parseColor("#F35AC7"))
        binding.btnStop.setBackgroundColor(Color.LTGRAY)
    }

    fun restartFun():Unit {
        pauseTime = 0L
        binding.chrono.base = SystemClock.elapsedRealtime()
        binding.chrono.stop()

        binding.btnStart.isEnabled = true
        binding.btnStop.isEnabled = false
        binding.btnReset.isEnabled = false
        binding.btnStart.setBackgroundColor(Color.parseColor("#F35AC7"))
        binding.btnStop.setBackgroundColor(Color.LTGRAY)
        binding.btnReset.setBackgroundColor(Color.LTGRAY)
    }
}