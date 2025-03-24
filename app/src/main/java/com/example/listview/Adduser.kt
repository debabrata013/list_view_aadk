package com.example.listview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Adduser : AppCompatActivity() {

    private lateinit var dbHelper: DbHelper
    private lateinit var etUsername: EditText
    private lateinit var etDob: EditText
    private lateinit var btnAddUser: Button
    private lateinit var btnReset: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)

        // Initialize database helper
        dbHelper = DbHelper(this)

        // Initialize UI components
        etUsername = findViewById(R.id.etUsername)
        etDob = findViewById(R.id.etDob)
        btnAddUser = findViewById(R.id.btnAddUser)
        btnReset = findViewById(R.id.btnReset)
        btnBack = findViewById(R.id.btnback)

        // Add User Button Click
        btnAddUser.setOnClickListener {
            addUser()
        }

        // Reset Fields Button Click
        btnReset.setOnClickListener {
            resetFields()
        }

        // Back Button Click
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //finish()
        // Closes current activity
        }
    }

    private fun addUser() {
        val name = etUsername.text.toString().trim()
        val dob = etDob.text.toString().trim()

        if (name.isEmpty() || dob.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        val insertedId = dbHelper.addUser(name, dob)
        if (insertedId > 0) {
            Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
            resetFields()
        } else {
            Toast.makeText(this, "Error adding user!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetFields() {
        etUsername.text.clear()
        etDob.text.clear()
    }
}