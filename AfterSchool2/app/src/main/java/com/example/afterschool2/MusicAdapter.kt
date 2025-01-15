package com.example.afterschool2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afterschool2.databinding.ItemBinding

data class MusicItem(val title: String, val artist: String, val resId: Int) //(제목, 아티스트, 리소스 ID)
class MusicAdapter(private val musicList: List<MusicItem>, //음악 목록
    private val onItemClick: (MusicItem, Int) -> Unit //항목 클릭 시 동작 정의
    ) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    inner class MusicViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val music = musicList[position]
        holder.binding.txtTitle.text = music.title
        holder.binding.txtArtist.text = music.artist
        holder.binding.root.setBackgroundColor(
            if (position == (holder.binding.root.context as MainActivity).selectedPosition) {
                android.graphics.Color.parseColor("#DQ7CE6")
            }
            else {
                android.graphics.Color.TRANSPARENT //선택되지 않은 항목은 투명
            }
        )
        holder.binding.root.setOnClickListener { //아이템 이벤트 처리
            onItemClick(music, position)
        }
    }

    override fun getItemCount(): Int = musicList.size
}