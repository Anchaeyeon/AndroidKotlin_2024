package com.example.app1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbHelper: MyDBHelper
    private lateinit var adapter: CartAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        dbHelper = MyDBHelper(requireContext())
        loadCartItems()
        return binding.root
    }
    private fun loadCartItems() {
        val cursor = dbHelper.getCartItems()
        val cartItems = mutableListOf<CartItem>()
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(MyDBHelper.COLUMN_ID))
                val name = it.getString(it.getColumnIndexOrThrow(MyDBHelper.COLUMN_PRODUCT_NAME))
                cartItems.add(CartItem(id, name))
            }
        }
        adapter = CartAdapter(cartItems, dbHelper) { loadCartItems() }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }
    override fun onResume() {
        super.onResume()
        loadCartItems() // 프래그먼트가 바뀔때 데이터 새로고침
    }
}
