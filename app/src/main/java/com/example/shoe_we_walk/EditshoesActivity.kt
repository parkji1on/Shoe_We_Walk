package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EditshoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editshoes)

        val nametext :TextView = findViewById(R.id.EditShoesTitleText)
        nametext.text = MainActivity.nickname + "님의 신발"

    }
}