package com.example.app1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.databinding.ItemCartBinding

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val dbHelper: MyDBHelper,
    private val onItemDeleted: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem, dbHelper, onItemDeleted)
    }
    override fun getItemCount(): Int = cartItems.size
    inner class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem, dbHelper: MyDBHelper, onItemDeleted: () -> Unit) {
            binding.productName.text = cartItem.name
            binding.btnDel.setOnClickListener {
                dbHelper.deleteCartItem(cartItem.id)
                onItemDeleted()
            }
        }
    }
}