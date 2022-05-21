package com.bytedance.jstu.homework

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.R

class ItemsActivity : AppCompatActivity() {
    private val dbHelper = MyDBHelper(this, "todolist.db", 1)
    private var db: SQLiteDatabase? = null

    private val addItem: Button by lazy {
        findViewById(R.id.add_item)
    }
    private val refreshBut: Button by lazy{
        findViewById(R.id.refresh_menu)
    }

    private var titleText: EditText?=null
    private var DesText: EditText?=null
    private var data= mutableListOf<Item>()

    fun updateList(){
        data.clear()
        val cursor = (db?: dbHelper.writableDatabase).query("item", null, null, null, null, null, null, null)

        if (cursor.moveToLast()){
            do{
                val name = cursor.getString(cursor.getColumnIndex("title"))
                val info = cursor.getString(cursor.getColumnIndex("content"))
                val flag = cursor.getInt(cursor.getColumnIndex("flag"))
                data.add(Item(name, info, flag))
            } while (cursor.moveToPrevious())
        }
        cursor.close()
        val recyclerView = findViewById<RecyclerView>(R.id.items_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ItemAdapter(this)

        adapter.setContentList(data)

        recyclerView.adapter = adapter
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        db = dbHelper.writableDatabase

        titleText = findViewById(R.id.item_title)
        DesText = findViewById(R.id.item_des)

        addItem.setOnClickListener{
            val values = ContentValues().apply {
                if(titleText?.text == null) return@apply

                put("title", "${titleText?.text}")
                put("content", "${DesText?.text}")
            }

            db?.insert("item", null, values)

            updateList()
        }
        refreshBut.setOnClickListener{
            updateList()
        }

        updateList()
//        val data = arrayListOf<Item>()
    }


}