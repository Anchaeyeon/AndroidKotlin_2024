package com.example.andtest5

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object { //static 멤버 변수 선언
        val DATABASE_NAME = "memoDB.db"
        val DATABASE_VER = 1
        val TABLE_NAME = "memo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table ${TABLE_NAME}(_id INTEGER PRIMARY KEY autoincrement, content text, datetime INTEGER)"
        db!!.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists ${TABLE_NAME}")
        onCreate(db)
    }

    fun insertMemo(memo: Memo) {
        val sql = "insert into ${TABLE_NAME}(content, datetime) values(?,?);"
        val db = writableDatabase
        var args = arrayOf(memo.content, memo.datetime)
        db.execSQL(sql, args)
        db.close()
    }
    @SuppressLint("Range")
    fun selectMemo(): MutableList<Memo> {
        var data = mutableListOf<Memo>()
        val sql = "select * from ${TABLE_NAME}"
        val db = readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val memo = Memo()
                memo.num = cursor.getLong(0)
                memo.content = cursor.getString(1)
                memo.datetime = cursor.getLong(2)
                data.add(memo)
            }
        }
        cursor.close()
        db.close()
        return data
    }

    fun updateMemo(memo: Memo) {
        val sql = "update ${TABLE_NAME} set content=?, datetime=? where _id=?"
        val args = arrayOf(memo.content, memo.datetime, memo.num)
        val db = writableDatabase
        db.execSQL(sql, args)
        db.close()
    }

    fun deleteMemo(memo: Memo) {
        val sql = "delete from ${TABLE_NAME} where _id=?"
        val args = arrayOf(memo.num)
        val db = writableDatabase
        db.execSQL(sql, args)
        db.close()
    }
}