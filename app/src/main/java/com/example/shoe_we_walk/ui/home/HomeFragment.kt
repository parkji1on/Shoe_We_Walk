package com.example.shoe_we_walk.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.shoe_we_walk.*
import com.example.shoe_we_walk.Data.Auth.loginflag
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.Data.Work
import com.example.shoe_we_walk.Util.CircleTransformation
import com.example.shoe_we_walk.Util.FinishWorkDialog
import com.example.shoe_we_walk.Util.getCalorie
import com.example.shoe_we_walk.Util.user
import com.example.shoe_we_walk.databinding.FragmentHomeBinding
import com.example.shoe_we_walk.databinding.FragmentMarketBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Collections.min
import kotlin.math.min

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    private lateinit var binding: FragmentHomeBinding
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseStartBtn.setOnClickListener {//운동 시작
            startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))       //시작시간 저장
            val intent = Intent(requireContext(), WalkActivity::class.java)
            launcher.launch(intent)
        }
        binding.HomeEditShoesText.setOnClickListener{
            val intent = Intent(activity, EditshoesActivity::class.java)
            startActivity(intent)//신발 수정으로 이동
        }
        binding.HomeShoesImage.setOnClickListener {
            val intent = Intent(activity, EditshoesActivity::class.java)
            startActivity(intent)//신발 수정으로 이동
        }
        binding.HomeUserTitleText.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)//로그인 시작
        }

        if(loginflag) {
            binding.root.findViewById<TextView>(R.id.HomeUserTitleText).text = nickname

            val imageView :ImageView = binding.root.findViewById(R.id.HomeProfileImage)
            Picasso.get()
                .load(profileImage)
                .transform(CircleTransformation())
                .into(imageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}