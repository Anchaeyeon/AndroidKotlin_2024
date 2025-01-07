package com.example.andtest06

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtest06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.op.setSelection(0) //기본연산자 +
        binding.btnResult.setOnClickListener {
            var x = binding.edX.text.toString().toInt()
            var y = binding.edY.text.toString().toInt()
            var result = 0

            when (binding.op.selectedItemPosition) {
                0 -> result = x+y
                1 -> result = x-y
                2 -> result = x*y
                3 -> result = x%y
            }
            binding.txtResult.text = result.toString()
        }
    }
}
