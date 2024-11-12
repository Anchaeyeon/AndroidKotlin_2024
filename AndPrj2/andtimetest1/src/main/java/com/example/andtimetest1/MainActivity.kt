package com.example.andtimetest1

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtimetest1.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var sYear:Int = 0
    var sMonth:Int = 0
    var sDay = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val localDay : LocalDate = LocalDate.now() //현재 시스템의 날짜를 가져옴
        sYear = localDay.year; sMonth = localDay.monthValue; sDay = localDay.dayOfMonth

        binding.calView.setOnDateChangeListener { calendarView, year, month, day ->
            sYear = year
            sMonth = month+1 //1월 >> 0월 시작
            sDay = day
        }

        binding.btnStart.setOnClickListener {
            binding.chrono.base = SystemClock.elapsedRealtime()
            binding.chrono.start()
            binding.chrono.setTextColor(Color.RED)
            binding.txt.text = ""
        }

        binding.btnStop.setOnClickListener {
            binding.chrono.stop();
            binding.chrono.setTextColor(Color.BLUE)
            var str = sYear.toString() + "년" + sMonth.toString() + "월" + sDay.toString() + "일 ";
            binding.txt.text = str + binding.timePick.hour.toString() + "시" + binding.timePick.minute.toString() + "분 예약됨"
        }

        //라디오버튼 클릭 처리
        binding.rbCal.setOnClickListener {
            binding.calView.visibility = View.VISIBLE;
            binding.timePick.visibility = View.INVISIBLE;
        }
        binding.rbTime.setOnClickListener {
            binding.calView.visibility = View.INVISIBLE;
            binding.timePick.visibility = View.VISIBLE;
        }
    }
}