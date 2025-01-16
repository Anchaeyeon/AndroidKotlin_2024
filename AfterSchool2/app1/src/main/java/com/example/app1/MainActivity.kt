package com.example.app1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.app1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var bannerAdapter: BannerAdapter
    private val bannerImages = listOf(
        //배너 이미지 리소스
        R.drawable.avocado,
        R.drawable.egg,
        R.drawable.sweet_potato
    )
    private var currentBannerIndex = 0
    private val handler = Handler(Looper.getMainLooper()) //이미지 2초마다 변경 (UI 업데이트에 Handler 사용)

    private lateinit var binding : ActivityMainBinding //뷰 바인딩
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //배너 어댑터 설정
        bannerAdapter = BannerAdapter(bannerImages)
        binding.bannerViewPager.adapter = bannerAdapter

        //배너 indicator 설정
        binding.txtBannerIndicator.text = "1/${bannerImages.size}"
        binding.bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.txtBannerIndicator.text = "${position+1}/${bannerImages.size}"
            }
        })
        startBannerRotation() //배너 자동 로테이션 설정

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Products"
                1 -> "Cart"
                else -> ""
            }
        }.attach()
    }
    fun navigateToTab(tabIndex: Int) { binding.viewPager.currentItem = tabIndex }

    //배너 이미지 Rotation
    private fun startBannerRotation() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                currentBannerIndex = (currentBannerIndex+1) % bannerImages.size
                binding.bannerViewPager.setCurrentItem(currentBannerIndex, true)
                handler.postDelayed(this, 2000) //2초마다 로테이션
            }
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) //핸들러 정리
    }
}