package com.bytedance.jstu.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BasicUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_ui)

        findViewById<View>(R.id.button2).setOnClickListener {
            val intent = Intent()
            intent.setClass(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}