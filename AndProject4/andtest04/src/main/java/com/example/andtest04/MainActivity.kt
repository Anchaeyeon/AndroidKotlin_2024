package com.example.andtest04

import android.app.ProgressDialog.show
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtest04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rg.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbSea -> binding.img.setImageResource(R.drawable.sea_pasta)
                R.id.rbTomato -> binding.img.setImageResource(R.drawable.tomato_pasta)
                R.id.rbCream -> binding.img.setImageResource(R.drawable.cream_pasta)
            }
        }

        binding.btn.setOnClickListener {
            var str = "메뉴를 먼저 선택해 주세요"
            when (binding.rg.getCheckedRadioButtonId()) {
                R.id.rbSea -> str = binding.rbSea.text.toString() + "\n주문하셨습니다."
                R.id.rbTomato -> str = binding.rbTomato.text.toString() + "\n주문하셨습니다."
                R.id.rbCream -> str = binding.rbCream.text.toString() + "\n주문하셨습니다."
            }
            Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
        }
    }
}