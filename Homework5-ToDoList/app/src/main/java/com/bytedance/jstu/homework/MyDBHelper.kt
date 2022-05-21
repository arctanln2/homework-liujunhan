package com.bytedance.jstu.homework

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDBHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {

    private val createTodoList = "create table item(" +
            "id integer primary key autoincrement," +
            "title text," +
            "content text," +
            "flag INTEGER DEFAULT 0)"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTodoList)
        Toast.makeText(context, "create item db success", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        if (oldVersion <= 1) {
//            db?.execSQL(createMessageList)
//        }
//        if (oldVersion <= 2) {
//            db?.execSQL(createUserList)
//        }
    }

}