package com.example.app1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.databinding.FragmentProductListBinding

class ProductFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: MyDBHelper
    private lateinit var productList: MutableList<Product>
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        dbHelper = MyDBHelper(requireContext())
        productList = loadProductList()
        adapter = ProductAdapter(productList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.btnBuy.setOnClickListener {
            productList.filter { it.isSelected }.forEach { product -> dbHelper.addToCart(product.name) }
            productList.forEach { it.isSelected = false } //체크 해제
            adapter.notifyDataSetChanged() //Update UI
            (activity as MainActivity).navigateToTab(1)
        }
        return binding.root
    }
    private fun loadProductList(): MutableList<Product> {
        return mutableListOf( Product("Product 1"), Product("Product 2"), Product("Product 3") )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}