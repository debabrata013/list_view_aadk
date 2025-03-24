package com.example.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val users = arrayOf(
            "Python", "Java", "Kotlin", "C++", "JavaScript", "Ruby", "Swift", "Go", "Rust", "TypeScript",
            "PHP", "C#", "Dart", "Perl", "Scala", "R", "Haskell", "Lua", "Objective-C", "F#",
            "Elixir", "Clojure", "Groovy", "Shell", "SQL", "MATLAB", "Julia", "Erlang", "VB.NET", "COBOL"
        )
        val listView: ListView = findViewById(R.id.listView)
        val listAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            users
        )

        listView.adapter = listAdapter
    }
}