package com.example.andproject6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andproject6.databinding.FragmentInputBinding

class InputFragment(private val onDeposit: (Int) -> Unit) : Fragment() {
    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.btnInputOk.setOnClickListener {
            val amount = binding.edtInput.text.toString().toIntOrNull() ?: 0
            onDeposit(amount)
            binding.edtInput.setText("0")
        }
        return binding.root
    }
}