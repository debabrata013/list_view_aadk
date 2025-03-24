package com.example.listview

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.listview.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = arrayOf(
            "Python", "JavaScript", "Ruby", "Swift", "Go", "Rust", "TypeScript",
            "Haskell", "Lua", "Objective-C", "F#", "SQL", "MATLAB", "Julia", "Erlang", "VB.NET", "COBOL"
        )

        val listView: ListView = findViewById(R.id.listView)
        val listAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            users
        )

        listView.adapter = listAdapter

        // Find the button by ID
        val btnNavigate = findViewById<Button>(R.id.button)

        // Set click listener to navigate to AddUser activity
        btnNavigate.setOnClickListener {
            val intent = Intent(this, Adduser::class.java)
            startActivity(intent)
        }

    }
}
