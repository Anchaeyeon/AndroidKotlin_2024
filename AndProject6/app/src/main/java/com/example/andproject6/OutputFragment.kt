package com.example.andproject6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andproject6.databinding.FragmentOutputBinding

class OutputFragment(private val onWithdraw: (Int) -> Unit) : Fragment() {
    private lateinit var binding: FragmentOutputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOutputBinding.inflate(inflater, container, false)

        binding.btnOutputOk.setOnClickListener {
            val amount = binding.edtOutput.text.toString().toIntOrNull() ?: 0
            onWithdraw(amount)
            binding.edtOutput.setText("0")
        }
        return binding.root
    }
}