package com.example.listview

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    companion object {
        const val DB_NAME = "ser.db"
        const val VERSION = 1
        const val TABLE_NAME = "user_table"
        const val ID = "id"
        const val NAME = "name"
        const val DOB = "dob"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $TABLE_NAME (
                $ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $NAME TEXT, 
                $DOB TEXT
            )
        """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert a new user
    fun addUser(name: String, dob: String): Long {
        writableDatabase.use { db ->
            val values = ContentValues().apply {
                put(NAME, name)
                put(DOB, dob)
            }
            return db.insert(TABLE_NAME, null, values)
        }
    }

    // Update a user's details
    fun updateUser(id: Int, name: String, dob: String): Int {
        writableDatabase.use { db ->
            val values = ContentValues().apply {
                put(NAME, name)
                put(DOB, dob)
            }
            return db.update(TABLE_NAME, values, "$ID = ?", arrayOf(id.toString()))
        }
    }

    // Delete a user by ID
    fun deleteUser(id: Int): Int {
        writableDatabase.use { db ->
            return db.delete(TABLE_NAME, "$ID = ?", arrayOf(id.toString()))
        }
    }

    // Get all users from the database
    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()
        readableDatabase.use { db ->
            val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            cursor.use {
                while (it.moveToNext()) {
                    val id = it.getInt(it.getColumnIndexOrThrow(ID))
                    val name = it.getString(it.getColumnIndexOrThrow(NAME))
                    val dob = it.getString(it.getColumnIndexOrThrow(DOB))
                    userList.add(User(id, name, dob))
                }
            }
        }
        return userList
    }
}

// Data class to store user details
data class User(val id: Int, val name: String, val dob: String)
