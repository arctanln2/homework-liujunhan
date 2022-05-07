package com.bytedance.jstu.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        addLesson("Android小作业一 题库", BasicUIActivity::class.java)
        addLesson("一键三连", ShineActivity::class.java)

    }
    private fun addLesson(text: String, activityClass: Class<*>) {
        val btn = AppCompatButton(this)
        btn.text = text
        btn.textSize = 40f
        btn.isAllCaps = false // 字母大小写
        findViewById<ViewGroup>(R.id.container).addView(btn) //在xml里添加
        btn.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@MainActivity, activityClass)
            }) // 按钮触发事件
        }
    }
}