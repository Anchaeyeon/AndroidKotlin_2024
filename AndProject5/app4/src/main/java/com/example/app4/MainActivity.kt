package com.example.app4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //프래그먼트 추가하기
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, FirstFragment()).commit()
        }
        binding.btnSwitch.setOnClickListener { switchFragment() }
    }

    private fun switchFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        //현재 프래그먼트 확인
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is FirstFragment) {
            transaction.replace(R.id.fragment_container, SecondFragment())
        }
        else {
            transaction.replace(R.id.fragment_container, FirstFragment())
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }
}