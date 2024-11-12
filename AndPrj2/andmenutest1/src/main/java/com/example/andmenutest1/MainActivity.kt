package com.example.andmenutest1

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andmenutest1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        //메뉴 xml 연결하기
        var mIn = menuInflater
        mIn.inflate(R.menu.menu1, menu) //가져온 xml을 코틀린으로 넣어주기

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemGreen -> binding.main.setBackgroundColor(Color.GREEN)
            R.id.itemPink -> binding.main.setBackgroundColor(Color.MAGENTA)
            R.id.itemWhite -> binding.main.setBackgroundColor(Color.WHITE)
            R.id.itemRotate -> binding.btn.setRotation((45).toFloat()) //(angle+=45)%360
            R.id.itemDefault -> binding.btn.setRotation((0).toFloat())
        }
        return true
    }
}