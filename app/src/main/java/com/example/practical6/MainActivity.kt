package com.example.practical6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun task1(view: View) {
        val intent = Intent(this,Task1::class.java)
        startActivity(intent)
    }

    fun task2(view: View) {
        val intent = Intent(this,Task2::class.java)
        startActivity(intent)
    }

    fun task3(view: View) {
        val intent = Intent(this,Task3::class.java)
        startActivity(intent)
    }

    fun task4(view: View) {
        val intent = Intent(this,Task4::class.java)
        startActivity(intent)
    }
}