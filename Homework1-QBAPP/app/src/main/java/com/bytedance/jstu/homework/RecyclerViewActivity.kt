package com.bytedance.jstu.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.AdapterView
import android.widget.Button

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)


        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = ItemAdapter()
        val data = (1..100).map { "${it}.两数之和" }
        adapter.setContentList(data)

        rv.adapter = adapter

//        val questionCount=adapter.itemCount
//        for (index in 0 until questionCount){
//            val btn3= adapter.getItemViewType(index)
//
//        }
        val et = findViewById<EditText>(R.id.words_et)
        // editText实例的设置 并且Listener监听行动
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                adapter.setFilter(p0.toString())
            }
        })

    }
}