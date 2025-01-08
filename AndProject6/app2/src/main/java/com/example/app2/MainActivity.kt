package com.example.app2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private var exp = "" //수식 전달
    private var currentInput = "" //숫자 입력
    private var firstOperand: Int? = null //첫 번째 피연산자
    private var operator: String? = null //연산자
    private var ch = 1 //두 번째 연산자 입력 체크

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numBtns = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )

        val opBtns = mapOf(
            "+" to binding.btnAdd,
            "-" to binding.btnSubtract,
            "*" to binding.btnMultiply,
            "/" to binding.btnDivide
        )
        numBtns.forEachIndexed { index, button ->
            button.setOnClickListener{
                currentInput += index.toString()
                binding.tvInput.text = currentInput
                if (ch==0)
                    binding.tvExp.text = binding.tvExp.text.toString() + currentInput
            }
        }

        //연산자 버튼 클릭 이벤트
        opBtns.forEach { (op, button) ->
            button.setOnClickListener{
                if (currentInput.isNotEmpty()) {
                    firstOperand = currentInput.toInt()
                    operator = op
                    currentInput = ""
                    exp = "$firstOperand $op"
                    binding.tvExp.text = exp
                    ch = 0
                }
            }
        }

        //"=" 버튼 클릭 이벤트
        binding.btnEquals.setOnClickListener {
            if (currentInput.isNotEmpty() && firstOperand!=null && operator!=null) {
                val secondOperand = currentInput.toInt()
                val result = when (operator) {
                    "+" -> firstOperand!! + secondOperand
                    "-" -> firstOperand!! - secondOperand
                    "*" -> firstOperand!! * secondOperand
                    "/" -> if (secondOperand != 0) firstOperand!!/secondOperand else "오류: 0으로 나눌 수 없음"
                    else -> "오류"
                }
                exp += secondOperand.toString()
                binding.tvExp2.text = "식: " + exp
                binding.tvResult.text = "결과: $result"
                exp = ""; ch = 1
                currentInput = ""
                firstOperand = null
                operator = null
                binding.tvInput.text = "0"
                binding.tvExp.text = "";
            }
        }

        //"C" 버튼 클릭 이벤트
        binding.btnClear.setOnClickListener {
            exp = ""; currentInput = ""
            firstOperand = null; operator = null
            binding.tvInput.text = "0"
            binding.tvResult.text = "결과: 0"
            binding.tvExp.text = ""
            binding.tvExp2.text = ""
        }
    }
}