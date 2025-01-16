package com.example.app1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "shop.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_CART = "cart"
        const val COLUMN_ID = "id"
        const val COLUMN_PRODUCT_NAME = "product_name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createCartTable = "CREATE TABLE $TABLE_CART (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_PRODUCT_NAME TEXT NOT NULL);"
        db.execSQL(createCartTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CART")
        onCreate(db)
    }

    fun addToCart(productName: String) {
        writableDatabase.use { db->
            val values = ContentValues().apply { put(COLUMN_PRODUCT_NAME, productName) }
            db.insert(TABLE_CART, null, values)
        }
    }

    fun getCartItem(): Cursor{ return readableDatabase.query(TABLE_CART, null, null, null, null, null, null) }
    fun deleteCartItem(id: Int) {
        writableDatabase.use { db -> db.delete(TABLE_CART, "$COLUMN_ID=?", arrayOf(id.toString())) }
    }
}