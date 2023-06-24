package com.example.shoe_we_walk

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.shoe_we_walk.Data.Work
import com.example.shoe_we_walk.Data.WorkRegisterRequest
import com.example.shoe_we_walk.Retrofit.insertWork
import com.example.shoe_we_walk.Util.*
import com.example.shoe_we_walk.databinding.FragmentHomeBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var startTime: String
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK && result.data != null) {
            val totalTime = result.data!!.getIntExtra("총 시간", 0)            //Int -> ms
            val totalDist = result.data!!.getDoubleExtra("총 거리", 0.0)       //Double -> m
            val totalStep = result.data!!.getIntExtra("총 걸음수", 0)           //Int -> 보
            val data = Work(
                startTime,
                totalTime,
                totalStep,
                totalDist.toFloat(),
                getCalorie(user.weight, totalTime/60000)
            )
            val dlg = FinishWorkDialog(data)
            dlg.isCancelable = false                //dialog외의 다른 곳을 눌러도 없어지지 않는다.
            dlg.show(parentFragmentManager, "Work Finished!")

        } else {
        Toast.makeText(context, "데이터가 전달되지 못했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseStartBtn.setOnClickListener {//운동 시작
            startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))       //시작시간 저장
            val intent = Intent(requireContext(), WalkActivity::class.java)
            launcher.launch(intent)
        }
    }
}