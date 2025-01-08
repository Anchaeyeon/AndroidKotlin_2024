package com.example.andproject6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andproject6.databinding.FragmentDataBinding

class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        updateBalance(MainActivity.total) //초기값 (Companion Object 활용)
        return binding.root
    }
    fun updateBalance (data: Int) { //받은 데이터 수정
        binding.txtData.text = "잔액: $data 원"
    }
}