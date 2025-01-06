package com.example.andtest05

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andtest05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var price: IntArray = intArrayOf(25000, 15000, 10000)
        var edts = arrayOf<EditText>(binding.edPiz, binding.edSpa, binding.edSal)

        binding.btnInit.setOnClickListener {
            for (i in 0 until edts.size) { edts[i].setText("0") } //for (data in edts) {data.setText("0")} 도 가능
            binding.txtTotal.text = "주문금액 : 0"
            binding.txtNum.text = "주문개수 : 0"
            binding.chkMemCard.setChecked(true)
        }
        binding.btnCal.setOnClickListener {
            var sum = 0
            var num = 0
            for (i in 0 until price.size) {
                var data = edts[i].text.toString().toInt()
                sum += data * price[i]
                num += data
            }
            if (binding.chkMemCard.isChecked) sum = (sum*0.8).toInt()
            binding.txtTotal.text = "주문금액 : " + sum.toString() + "원"
            binding.txtNum.text = "주문개수 : " + num.toString()
        }
    }
}