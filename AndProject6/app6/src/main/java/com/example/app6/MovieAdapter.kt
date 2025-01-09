package com.example.app6

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app6.databinding.ListItemBinding

data class Movie(var image: Drawable, var title: String, var genre: String)
class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
class MovieAdapter(val datas: MutableList<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    //리사이클러뷰 아이템 이벤트 처리를 위한 구현
    interface ItemClick { fun onClick(view: View, position: Int) }
    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClick?.onClick(it, position) }
        val binding = (holder as MyViewHolder).binding
        var m: Movie = datas[position]
        binding.txtTitle.text = m.title
        binding.txtGenre.text = m.genre
        binding.imgPoster.setImageDrawable(m.image)
    }

    override fun getItemCount(): Int { return datas.size; }
}