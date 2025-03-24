package com.example.listview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Dbhelper(cointext: Context): SQLiteOpenHelper( cointext, DB_NAME,null, Verson) {

    companion object{
        val DB_NAME="ser"
        val Verson=1
        val TABLE_NAME="user_table"
        val ID="id"
        val name="name"
        val dob="dob"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                $ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $name TEXT, 
                $dob TEXT
            )
        """.trimIndent()

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME") // Delete old table if exists
        onCreate(db) // Recreate table
    }
}