package com.example.andproject6

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: AppCompatActivity, private val fragments: List<Fragment>) : FragmentStateAdapter(activity){ //생성자
    override fun getItemCount(): Int = fragments.size //오버라이딩
    override fun createFragment(position: Int): Fragment = fragments[position]
}