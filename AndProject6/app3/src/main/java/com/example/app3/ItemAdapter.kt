package com.example.app3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Item(val name: String, var isChecked: Boolean)
class ItemAdapter (
    private val itemList: MutableList<Item>,
    private val onItemCheckedChange: (Int, Boolean) -> Unit):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.textView)
            val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            var item = itemList[position]
            holder.textView.text = item.name
            holder.checkBox.isChecked = item.isChecked
            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                onItemCheckedChange(position, isChecked)
            }
        }

        override fun getItemCount(): Int = itemList.size

        //항목 추가
        fun addItem(item: Item) {
            itemList.add(item)
            notifyItemInserted(itemList.size-1)
        }

        //선택된 항목 삭제
        fun removeSelectedItems() {
            var iterator = itemList.iterator()
            while (iterator.hasNext()) {
                if (iterator.next().isChecked) {
                    iterator.remove()
                }
            }
            notifyDataSetChanged()
        }
    }