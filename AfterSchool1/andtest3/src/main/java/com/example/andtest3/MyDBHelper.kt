package com.example.andtest3

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    companion object {
        val DATABASE_NAME = "user.db"
        val DATABASE_VER = 1
        val TABLE_NAME = "userTBL"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table $TABLE_NAME(_id INTEGER PRIMARY KEY autoincrement, name text, tel text)"
        db!!.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(mem: User) {
        val sql = "insert into ${TABLE_NAME}(name, tel) values('"+mem.name+"', '"+mem.tel+"');"
        val db = writableDatabase
        db.execSQL(sql)
        db.close()
    }

    @SuppressLint("Range") //@SuppressLint: 경고를 무시하는 명령어, "Range": getColumnIndex()와 관련된 경고
    fun selectUser(): MutableList<User> {
        var data = mutableListOf<User>()
        val sql = "select * from ${TABLE_NAME}"
        val rd = readableDatabase
        val cursor = rd.rawQuery(sql, null)
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val num = cursor.getInt(cursor.getColumnIndex("_id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val tel = cursor.getString(cursor.getColumnIndex("tel"))
                data.add(User(num, name, tel))
            }
        }
        cursor.close()
        rd.close()
        return data
    }

    fun updateUser(mem: User) {
        val sql = "update {$TABLE_NAME} set name='${mem.name}', tel='${mem.tel}' where _id=${mem.num}"
        val db = writableDatabase
        db.execSQL(sql)
        db.close()
    }

    fun deleteUser(num: Int) {
        val delete = "delete from $TABLE_NAME where _id=${num}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

    fun deleteAll() {
        val delete = "delete from $TABLE_NAME"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }
}