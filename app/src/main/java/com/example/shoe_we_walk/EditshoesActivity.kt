package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.databinding.ActivityEditshoesBinding
import com.example.shoe_we_walk.databinding.ActivityWalkBinding

class EditshoesActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditshoesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditshoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editShoesAbleJibbitsText.text = nickname + "님의 신발"
    }
}