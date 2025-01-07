package com.example.app3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sw.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) binding.lay.visibility = View.VISIBLE
            else binding.lay.visibility = View.INVISIBLE
        }

        binding.btnClear.setOnClickListener {
            binding.edName.setText("")
            binding.edMid.setText("")
            binding.edFinal.setText("")
            binding.edReport.setText("")
            binding.edAttend.setText("")
            binding.rg.clearCheck()
        }

        binding.btnCal.setOnClickListener {
            val sum = (binding.edMid.getText().toString().toDouble() * 0.3 +
                        binding.edFinal.getText().toString().toDouble() * 0.3 +
                        binding.edReport.getText().toString().toDouble() * 0.2 +
                        binding.edAttend.getText().toString().toDouble() * 0.2).toInt()

            var str = "F"
            if (sum >= 90) str = "A"
            else if (sum >= 80) str = "B"
            else if (sum >= 70) str = "C"
            else str = "F"

            Toast.makeText(this, "학점 : " + str, Toast.LENGTH_SHORT).show()
        }
    }
}