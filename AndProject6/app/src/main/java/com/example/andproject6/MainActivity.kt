package com.example.andproject6

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andproject6.MainActivity.Companion.total
import com.example.andproject6.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        var total = 10000
    } //먼저 선언하기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf( //Fragment 생성
            DataFragment(), InputFragment { deposit(it) }, OutputFragment { withdraw(it) }
        ) //ViewPager2 Adapter 설정
        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position -> //TabLayout 연결
            tab.text = when (position) {
                0 -> "잔액"
                1 -> "입금"
                2 -> "출금"
                else -> "탭 $position"
            }
        }.attach()

        val tab = (binding.tabLayout.getChildAt(0) as? ViewGroup)?.getChildAt(0) as? ViewGroup //0탭 가져오기
        tab?.setBackgroundColor(Color.rgb(244, 244, 127)) //0탭 배경 설정

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) { //탭 선택
                tab?.view?.setBackgroundColor(Color.rgb(244, 244, 127))
                val position = 0 //val position = tab?.position ?: 0 (첫번째 프래그먼트)
                val fragment =
                    supportFragmentManager.findFragmentByTag("f$position") as? DataFragment //현재 선택된 프래그먼트의 값 업데이트
                fragment?.updateBalance(total) //total : Companion Object
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundColor(Color.rgb(248, 248, 241)) //탭 선택 해제
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }) //이미 선택된 탭을 다시 선택할 떄의 동작
    }

    private fun deposit(amount: Int) {
        total += amount
        Toast.makeText(this, "$amount 원 입금. 잔액: $total 원", Toast.LENGTH_SHORT).show()
    }

    private fun withdraw(amount: Int) {
        total -= amount
        Toast.makeText(this, "$amount 원 출금. 잔액: $total 원", Toast.LENGTH_SHORT).show()
    }
}