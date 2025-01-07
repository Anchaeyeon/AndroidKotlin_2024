package com.example.app3

import android.os.Binder
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflate = menuInflater
        mInflate.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemExit -> finish()
            R.id.itemClear -> {
                binding.edName.setText("")
                binding.edMid.setText("")
                binding.edFinal.setText("")
                binding.edReport.setText("")
                binding.edAttend.setText("")
                binding.rg.clearCheck()
            }
        }
        return true
    }

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
            val dlgView = View.inflate(this@MainActivity, R.layout.dlg, null)
            val sum = (binding.edMid.getText().toString().toDouble() * 0.3 +
                        binding.edFinal.getText().toString().toDouble() * 0.3 +
                        binding.edReport.getText().toString().toDouble() * 0.2 +
                        binding.edAttend.getText().toString().toDouble() * 0.2).toInt()

            var score = dlgView.findViewById<TextView>(R.id.txtScore)
            var img = dlgView.findViewById<ImageView>(R.id.img)
            var str = ""

            when (binding.rg.checkedRadioButtonId) {
                R.id.rb1 -> str += "1학년"
                R.id.rb2 -> str += "2학년"
                R.id.rb3 -> str += "3학년"
            }

            score.text = str + binding.edName.text.toString() + "학생의 총점: " + sum.toString()
            if (sum >= 90) img.setImageResource(R.drawable.alphabet_a)
            else if (sum >= 80) img.setImageResource(R.drawable.alphabet_b)
            else if (sum >= 70) img.setImageResource(R.drawable.alphabet_c)
            else img.setImageResource(R.drawable.alphabet_f)

            val dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setView(dlgView)
            dlg.setTitle("학점계산결과")
            dlg.setPositiveButton("확인", null)
            dlg.show()
        }
    }
}