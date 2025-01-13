package com.example.andtest4

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Todo(var title: String="", var memo: String="", var writeDate: Long=0):java.io.Serializable //객체 타입으로 전달
class MyDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object { //static 멤버 변수 선언
        val DATABASE_NAME = "todo.db"
        val DATABASE_VER = 1
        val TABLE_NAME = "userTBL"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //_id : 0, title : 1, memo : 2, writedate : 3
        var sql = "create table $TABLE_NAME(_id INTEGER PRIMARY KEY autoincrement, title text, memo text, writedate long)"
        db!!.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    //@SuppressLint: 경고를 무시하는 명령어, "Range": getColumnIndex()와 관련된 경고
    @SuppressLint("Range")
    fun allTodo(): MutableList<Todo> {
        var data = mutableListOf<Todo>()
        val sql = "select * from ${TABLE_NAME}"
        val db = readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val todo = Todo()
                todo.title = cursor.getString(1)
                todo.memo = cursor.getString(2)
                todo.writeDate = cursor.getLong(3)
                data.add(todo)
            }
        }
        cursor.close()
        db.close()
        return data
    }

    fun addTodo(todo: Todo) {
        val sql = "insert into ${TABLE_NAME}(title, memo, writedate) values(?,?,?);"
        val db = writableDatabase;
        val args = arrayOf(todo.title, todo.memo, todo.writeDate)
        db.execSQL(sql, args)
        db.close()
    }

    fun updateTodo(todo: Todo) {
        val sql = "update ${TABLE_NAME} set title=?, memo=?, writedate=? where title=?"
        val args = arrayOf(todo.title, todo.memo, todo.writeDate, todo.title)
        val db = writableDatabase
        db.execSQL(sql, args)
        db.close()
    }

    fun deleteTodo(todo: Todo) {
        val sql = "delete from $TABLE_NAME where title=?"
        val args = arrayOf(todo.title)
        val db = writableDatabase
        db.execSQL(sql, args)
        db.close()
    }

    fun deleteAll() {
        val delete = "delete from $TABLE_NAME"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }
}