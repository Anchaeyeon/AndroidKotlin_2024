package com.example.andtest2

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.andtest2.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var startDegree: Float = 0f //초기 각도
    var endDegree: Float = 0f //끝 각도
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun rotate(v: View?) { //룰렛 터치시 호출되는 함수
        startDegree = endDegree //이전 정지 각도를 시작 각도로 설정
        val rand = Random()
        val degree_rand = rand.nextInt(360) //0~359 사이 정수
        endDegree = startDegree + 360 * 5 + degree_rand //5회전 + 회전 종료 각도 설정

        //초기 각도에서 종료 각도까지 회전(rotation)하는 애니메이션 생성
        val animation = ObjectAnimator.ofFloat(binding.roulette, "rotation", startDegree, endDegree)
        animation.interpolator = AccelerateDecelerateInterpolator() //가속도 조정 (점차 느려지게)
        animation.duration = (3000 - degree_rand).coerceAtLeast(1000).toLong() //최소 1초 보장, 지속 시간 동적 설정
        animation.start()
    }
}