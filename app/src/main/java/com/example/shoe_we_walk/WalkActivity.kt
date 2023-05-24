package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoe_we_walk.Util.setStatusBarTransparent
import com.example.shoe_we_walk.databinding.ActivityWalkBinding
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class WalkActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var binding: ActivityWalkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setStatusBarTransparent()  //statusBar transparent

        binding.mapMv.onCreate(savedInstanceState)

        binding.exerciseFinishBtn.setOnClickListener {//운동종료
//            TODO("특정 정보를 가지고 HomeFragment로 이동")
        }
    }

    override fun onMapReady(p0: NaverMap) {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        super.onStart()
        binding.mapMv.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapMv.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapMv.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapMv.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapMv.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapMv.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapMv.onLowMemory()
    }
}