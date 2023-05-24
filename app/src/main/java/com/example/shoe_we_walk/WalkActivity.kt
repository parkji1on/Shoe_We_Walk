package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoe_we_walk.Util.setStatusBarTransparent
import com.example.shoe_we_walk.databinding.ActivityWalkBinding

class WalkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWalkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setStatusBarTransparent()  //statusBar transparent

        binding.exerciseFinishBtn.setOnClickListener {//운동종료
//            TODO("특정 정보를 가지고 HomeFragment로 이동")
        }
    }
}