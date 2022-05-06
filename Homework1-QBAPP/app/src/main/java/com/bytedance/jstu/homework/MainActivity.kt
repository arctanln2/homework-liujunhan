package com.bytedance.jstu.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.R
import android.widget.Toast


class MainActivity : AppCompatActivity() {
//    private fun onListItemClick(position: Int) {
//        Toast.makeText(this, mRepos[position].name, Toast.LENGTH_SHORT).show()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        addLesson("Android小作业一 题库", BasicUIActivity::class.java)
        addLesson("Android小作业一 题库", RecyclerViewActivity::class.java)

    }
    private fun addLesson(text: String, activityClass: Class<*>) {
        val btn = AppCompatButton(this)
        btn.text = text
        btn.isAllCaps = false // 字母大小写
        findViewById<ViewGroup>(R.id.container).addView(btn) //在xml里添加
        btn.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@MainActivity, activityClass)
            }) // 按钮触发事件
        }
    }
}