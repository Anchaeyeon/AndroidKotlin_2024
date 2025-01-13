package com.example.andtest4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andtest4.databinding.ItemBinding

class MyViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)
class MyAdapter(val datas: MutableList<Todo>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        = MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    interface ItemClick { fun onClick(view: View, position: Int) }
    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClick?.onClick(it, position)}

        val binding = (holder as MyViewHolder).binding
        var m: Todo = datas!![position]
        binding.txtTitle.text = m.title
        binding.txtMemo.text = m.memo
        binding.txtDate.text = android.text.format.DateFormat.format("yyyy/MM/dd", m.writeDate).toString()
    }

    override fun getItemCount(): Int { return datas?.size ?: 0 }
}