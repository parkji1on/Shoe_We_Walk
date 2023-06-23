package com.example.shoe_we_walk

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.shoe_we_walk.Util.Constant
import com.example.shoe_we_walk.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    val WALK_ACTIVITY_REQUEST_CODE = 100
    private val startTime: Long by lazy {
        SystemClock.elapsedRealtime()
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK && result.data != null){
            val totalTime = result.data!!.getStringExtra("총 시간")
            val totalDist = result!!.data!!.getDoubleExtra("총 거리", 0.0)
            val totalStep = result!!.data!!.getIntExtra("총 걸음수", 0)

            AlertDialog.Builder(context)
                .setTitle("운동 결과")
                .setMessage("총 시간 : "+totalTime+"\n총 거리 : $totalDist\n총 걸음수 : $totalStep")
                .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        //저장하는 기능
                    }

                }).create().show()
        } else {
            Toast.makeText(context, "데이터가 전달되지 못했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


            val intent = Intent(activity, WalkActivity::class.java) //제대로 동작하지 않음
//            val intent = Intent(requireContext(), WalkActivity::class.java)
//            launcher.launch(intent)
//            startActivityForResult(intent, WALK_ACTIVITY_REQUEST_CODE)
            startActivity(intent)
        }
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == WALK_ACTIVITY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK && data != null) {

                val totalTime = Constant.mformat.format(data.getDoubleExtra("총 시간", 0.0))
                val totalDist = data.getDoubleExtra("총 거리", 0.0)
                val totalStep = data.getIntExtra("총 걸음수", 0)

                AlertDialog.Builder(context)
                    .setTitle("운동 결과")
                    .setMessage("총 시간 : "+totalTime+"\n총 거리 : $totalDist\n총 걸음수 : $totalStep")
                    .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            //저장하는 기능
                        }

                    }).create().show()
            } else {
                Toast.makeText(context, "데이터가 전달되지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        } else super.onActivityResult(requestCode, resultCode, data)
    }
*/
}