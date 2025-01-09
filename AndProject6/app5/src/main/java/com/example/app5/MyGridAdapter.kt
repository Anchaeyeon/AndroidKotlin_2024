package com.example.app5

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app5.databinding.ItemBinding

data class Movie(var image: Drawable, var title: String)
class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
class MyGridAdapter(val datas: MutableList<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    interface ItemClick { fun onClick(view: View, position: Int) } //아이템 이벤트
    var itemClick: ItemClick?=null //아이템 이벤트

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClick?.onClick(it, position) } //아이템 이벤트
        val binding = (holder as MyViewHolder).binding
        val m: Movie = datas[position]
        binding.ivPoster.setImageDrawable(m.image)
    }

    override fun getItemCount(): Int { return datas.size; }
}