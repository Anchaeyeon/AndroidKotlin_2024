package com.example.andtest5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andtest5.databinding.ItemBinding
import java.text.SimpleDateFormat

data class Memo(var num:Long?=0, var content: String="", var datetime: Long=0)
class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var helper: MyDBHelper? = null
    val datas = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        = MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        val memo = datas.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    inner class MyViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var mMemo: Memo? = null
        init {
            binding.buttonDelete.setOnClickListener {
                helper?.deleteMemo(mMemo!!)
                datas.remove(mMemo)
                notifyDataSetChanged()
            }
        }

        fun setMemo(memo: Memo) {
            binding.textNo.text = "${memo.num}" //문자열 변환 방법 memo.num.toString()
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
            this.mMemo = memo
        }
    }
}