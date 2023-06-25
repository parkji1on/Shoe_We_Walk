package com.example.shoe_we_walk

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoe_we_walk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        var loginflag = false

        var user_name :String = ""
        var nickname :String = ""
        var gender :String = ""
        var age :Int = 0
        var height :Int = 0
        var weight :Float = 0.0F
        var profileImage :String? = null

        var coin :Int = 0
        var total_item_cnt :Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        intent.getStringExtra("profileImage")
    }
}