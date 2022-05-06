package com.bytedance.jstu.homework

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.R

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
//    需要一个ViewHolder 自定义了一个ItemViewHolder
//    mutableListOf 可变列表 string类型
    private val contentList = mutableListOf<String>()
    private val filteredList = mutableListOf<String>()

//    private val itemClickListener: OnItemClickListener? = null
//    inflate方法
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = View.inflate(parent.context, R.layout.item_layout, null)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getItemCount(): Int = filteredList.size



    fun setContentList(list: List<String>) {
        contentList.clear()
        contentList.addAll(list)
        filteredList.clear()
        filteredList.addAll(list)
        notifyDataSetChanged()
    }

    fun setFilter(keyword: String?) {
        filteredList.clear()
        if (keyword?.isNotEmpty() == true) {
            filteredList.addAll(contentList.filter { it.contains(keyword) })
        } else {
            filteredList.addAll(contentList)
        }
        notifyDataSetChanged()
    }

//    interface OnItemClickListener {
//        fun onItemClick(str: String?, position: Int)
//    }
//    fun setItemClickListener(itemClickListener: OnItemClickListener?) {
//        itemClickListener = itemClickListener
//    }
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        // 找到对应文本框 并修改文本框的值
        private val tv = view.findViewById<TextView>(R.id.item_tv)
        private val btn = view.findViewById<Button>(R.id.button3)
        fun bind(content: String) {
            tv.text = content
        }
        override fun onClick(v: View) {
            //.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            val intent = Intent(v.context, BasicUIActivity::class.java)
//            intent.putExtra("text", textView.text)
            v.context.startActivity(intent)
        }
        init{
            btn.setOnClickListener(this)
        }
    }

}