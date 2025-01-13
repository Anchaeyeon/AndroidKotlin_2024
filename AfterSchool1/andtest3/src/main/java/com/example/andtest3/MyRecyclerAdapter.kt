package com.example.andtest3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andtest3.databinding.ItemBinding

data class User(var name: String, var tel: String)
class MyViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)
class MyRecyclerAdapter(val datas: MutableList<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //var helper: MyDBHelpe? = null
    interface ItemClick { fun onClick(view: View, position: Int) }
    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{ itemClick?.onClick(it, position) }
        val binding = (holder as MyViewHolder).binding
        var m: User = datas[position]
        binding.txtName.text = m.name
        binding.txtTel.text = m.tel
    }
    override fun getItemCount(): Int {
        return datas.size
    }
}