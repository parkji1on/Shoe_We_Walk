package com.example.shoe_we_walk.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoe_we_walk.*
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Auth.itemList
import com.example.shoe_we_walk.Data.Auth.loginflag
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.Data.Auth.total_item_cnt
import com.example.shoe_we_walk.Data.Work
import com.example.shoe_we_walk.Util.CircleTransformation
import com.example.shoe_we_walk.Util.getCalorie
import com.example.shoe_we_walk.Util.user
import com.example.shoe_we_walk.adapter.JibbitsAdapter
import com.example.shoe_we_walk.adapter.MyAllJibbitsAdapter
import com.example.shoe_we_walk.adapter.MyAllJibbitsData
import com.example.shoe_we_walk.databinding.FragmentHomeBinding
import com.example.shoe_we_walk.dialog.WalkFinishDialog
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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
            if(Auth.loginflag){
                val dialog = WalkFinishDialog(requireContext(), Work(
                    startTime,
                    totalTime,
                    totalStep,
                    totalDist.toFloat(),
                    getCalorie(Auth.weight.value!!, totalTime/60000)))
                dialog.showDialog()
            } else{
                val dialog = WalkFinishDialog(requireContext(), Work(
                    startTime,
                    totalTime,
                    totalStep,
                    totalDist.toFloat(),
                    getCalorie(Auth.weight.value!!, totalTime/60000)))
                dialog.showDialog()
            }

        } else {
            Toast.makeText(context, "데이터가 전달되지 못했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.HomeTitleText.text = randText() //상단문구 변경

        binding.exerciseStartBtn.setOnClickListener {//운동 시작
            startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))       //시작시간 저장
            val intent = Intent(requireContext(), WalkActivity::class.java)
            launcher.launch(intent)
        }

        binding.HomeEditShoesText.setOnClickListener{
            if(!loginflag)
                Toast.makeText(context, "로그인하여 지비츠를 꾸며보세요!", Toast.LENGTH_SHORT).show()
            else if(total_item_cnt.value == 0)
                Toast.makeText(context, "보유한 지비츠가 없어 \n꾸미기를 할 수 없습니다.", Toast.LENGTH_SHORT).show()
            else
            {
                val intent = Intent(activity, EditshoesActivity::class.java)
                startActivity(intent)//신발 수정으로 이동
            }
        }
        binding.HomeShoesImage.setOnClickListener {
            if(!loginflag)
                Toast.makeText(context, "로그인하여 지비츠를 꾸며보세요!", Toast.LENGTH_SHORT).show()
            else if(total_item_cnt.value == 0)
                Toast.makeText(context, "보유한 지비츠가 없어 \n꾸미기를 할 수 없습니다.", Toast.LENGTH_SHORT).show()
            else
            {
                val intent = Intent(activity, EditshoesActivity::class.java)
                startActivity(intent)//신발 수정으로 이동
            }
        }

        binding.HomeUserTitleText.setOnClickListener{
            if(!loginflag)
            {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)//로그인 시작
            }
        }

        val jibbits1 :ImageView = binding.MainJibbitzImage1
        val jibbits2 :ImageView = binding.MainJibbitzImage2
        val jibbits3 :ImageView = binding.MainJibbitzImage3
        val jibbits4 :ImageView = binding.MainJibbitzImage4
        val jibbits5 :ImageView = binding.MainJibbitzImage5
        val jibbits6 :ImageView = binding.MainJibbitzImage6
        val jibbits7 :ImageView = binding.MainJibbitzImage7
        val jibbits8 :ImageView = binding.MainJibbitzImage8
        val jibbits9 :ImageView = binding.MainJibbitzImage9
        val jibbits10 :ImageView = binding.MainJibbitzImage10

        fun setjibbits(){
            if(loginflag)
            {
                jibbits1.visibility = View.VISIBLE
                jibbits2.visibility = View.VISIBLE
                jibbits3.visibility = View.VISIBLE
                jibbits4.visibility = View.VISIBLE
                jibbits5.visibility = View.VISIBLE
                jibbits6.visibility = View.VISIBLE
                jibbits7.visibility = View.VISIBLE
                jibbits8.visibility = View.VISIBLE
                jibbits9.visibility = View.VISIBLE
                jibbits10.visibility = View.VISIBLE

                when(Auth.locationdata.value!!.location_1){
                    0 -> jibbits1.visibility = View.GONE
                    1 -> jibbits1.setImageResource(R.drawable.jibbits_bee_1)
                    2 -> jibbits1.setImageResource(R.drawable.jibbits_duck_1)
                    3 -> jibbits1.setImageResource(R.drawable.jibbits_cat_1)
                    4 -> jibbits1.setImageResource(R.drawable.jibbits_clover_1)
                    5 -> jibbits1.setImageResource(R.drawable.jibbits_flower_1)
                    6 -> jibbits1.setImageResource(R.drawable.jibbits_orange_1)
                    7 -> jibbits1.setImageResource(R.drawable.jibbits_paeng_1)
                    8 -> jibbits1.setImageResource(R.drawable.jibbits_bear_1)
                    9 -> jibbits1.setImageResource(R.drawable.jibbits_octopus_1)
                    10 -> jibbits1.setImageResource(R.drawable.jibbits_strawberry_1)
                }

                when(Auth.locationdata.value?.location_2){
                    0 -> jibbits2.visibility = View.GONE
                    1 -> jibbits2.setImageResource(R.drawable.jibbits_bee_2)
                    2 -> jibbits2.setImageResource(R.drawable.jibbits_duck_2)
                    3 -> jibbits2.setImageResource(R.drawable.jibbits_cat_2)
                    4 -> jibbits2.setImageResource(R.drawable.jibbits_clover_2)
                    5 -> jibbits2.setImageResource(R.drawable.jibbits_flower_2)
                    6 -> jibbits2.setImageResource(R.drawable.jibbits_orange_2)
                    7 -> jibbits2.setImageResource(R.drawable.jibbits_paeng_2)
                    8 -> jibbits2.setImageResource(R.drawable.jibbits_bear_2)
                    9 -> jibbits2.setImageResource(R.drawable.jibbits_octopus_2)
                    10 -> jibbits2.setImageResource(R.drawable.jibbits_strawberry_2)
                }

                when(Auth.locationdata.value?.location_3){
                    0 -> jibbits3.visibility = View.GONE
                    1 -> jibbits3.setImageResource(R.drawable.jibbits_bee_3)
                    2 -> jibbits3.setImageResource(R.drawable.jibbits_duck_3)
                    3 -> jibbits3.setImageResource(R.drawable.jibbits_cat_3)
                    4 -> jibbits3.setImageResource(R.drawable.jibbits_clover_3)
                    5 -> jibbits3.setImageResource(R.drawable.jibbits_flower_3)
                    6 -> jibbits3.setImageResource(R.drawable.jibbits_orange_3)
                    7 -> jibbits3.setImageResource(R.drawable.jibbits_paeng_3)
                    8 -> jibbits3.setImageResource(R.drawable.jibbits_bear_3)
                    9 -> jibbits3.setImageResource(R.drawable.jibbits_octopus_3)
                    10 -> jibbits3.setImageResource(R.drawable.jibbits_strawberry_3)
                }

                when(Auth.locationdata.value?.location_4){
                    0 -> jibbits4.visibility = View.GONE
                    1 -> jibbits4.setImageResource(R.drawable.jibbits_bee_4)
                    2 -> jibbits4.setImageResource(R.drawable.jibbits_duck_4)
                    3 -> jibbits4.setImageResource(R.drawable.jibbits_cat_4)
                    4 -> jibbits4.setImageResource(R.drawable.jibbits_clover_4)
                    5 -> jibbits4.setImageResource(R.drawable.jibbits_flower_4)
                    6 -> jibbits4.setImageResource(R.drawable.jibbits_orange_4)
                    7 -> jibbits4.setImageResource(R.drawable.jibbits_paeng_4)
                    8 -> jibbits4.setImageResource(R.drawable.jibbits_bear_4)
                    9 -> jibbits4.setImageResource(R.drawable.jibbits_octopus_4)
                    10 -> jibbits5.setImageResource(R.drawable.jibbits_strawberry_4)
                }

                when(Auth.locationdata.value?.location_5){
                    0 -> jibbits5.visibility = View.GONE
                    1 -> jibbits5.setImageResource(R.drawable.jibbits_bee_5)
                    2 -> jibbits5.setImageResource(R.drawable.jibbits_duck_5)
                    3 -> jibbits5.setImageResource(R.drawable.jibbits_cat_5)
                    4 -> jibbits5.setImageResource(R.drawable.jibbits_clover_5)
                    5 -> jibbits5.setImageResource(R.drawable.jibbits_flower_5)
                    6 -> jibbits5.setImageResource(R.drawable.jibbits_orange_5)
                    7 -> jibbits5.setImageResource(R.drawable.jibbits_paeng_5)
                    8 -> jibbits5.setImageResource(R.drawable.jibbits_bear_5)
                    9 -> jibbits5.setImageResource(R.drawable.jibbits_octopus_5)
                    10 -> jibbits5.setImageResource(R.drawable.jibbits_strawberry_5)
                }

                when(Auth.locationdata.value?.location_6){
                    0 -> jibbits6.visibility = View.GONE
                    1 -> jibbits6.setImageResource(R.drawable.jibbits_bee_6)
                    2 -> jibbits6.setImageResource(R.drawable.jibbits_duck_6)
                    3 -> jibbits6.setImageResource(R.drawable.jibbits_cat_6)
                    4 -> jibbits6.setImageResource(R.drawable.jibbits_clover_6)
                    5 -> jibbits6.setImageResource(R.drawable.jibbits_flower_6)
                    6 -> jibbits6.setImageResource(R.drawable.jibbits_orange_6)
                    7 -> jibbits6.setImageResource(R.drawable.jibbits_paeng_6)
                    8 -> jibbits6.setImageResource(R.drawable.jibbits_bear_6)
                    9 -> jibbits6.setImageResource(R.drawable.jibbits_octopus_6)
                    10 -> jibbits6.setImageResource(R.drawable.jibbits_strawberry_6)
                }

                when(Auth.locationdata.value?.location_7){
                    0 -> jibbits7.visibility = View.GONE
                    1 -> jibbits7.setImageResource(R.drawable.jibbits_bee_7)
                    2 -> jibbits7.setImageResource(R.drawable.jibbits_duck_7)
                    3 -> jibbits7.setImageResource(R.drawable.jibbits_cat_7)
                    4 -> jibbits7.setImageResource(R.drawable.jibbits_clover_7)
                    5 -> jibbits7.setImageResource(R.drawable.jibbits_flower_7)
                    6 -> jibbits7.setImageResource(R.drawable.jibbits_orange_7)
                    7 -> jibbits7.setImageResource(R.drawable.jibbits_paeng_7)
                    8 -> jibbits7.setImageResource(R.drawable.jibbits_bear_7)
                    9 -> jibbits7.setImageResource(R.drawable.jibbits_octopus_7)
                    10 -> jibbits7.setImageResource(R.drawable.jibbits_strawberry_7)
                }

                when(Auth.locationdata.value?.location_8){
                    0 -> jibbits8.visibility = View.GONE
                    1 -> jibbits8.setImageResource(R.drawable.jibbits_bee_8)
                    2 -> jibbits8.setImageResource(R.drawable.jibbits_duck_8)
                    3 -> jibbits8.setImageResource(R.drawable.jibbits_cat_8)
                    4 -> jibbits8.setImageResource(R.drawable.jibbits_clover_8)
                    5 -> jibbits8.setImageResource(R.drawable.jibbits_flower_8)
                    6 -> jibbits8.setImageResource(R.drawable.jibbits_orange_8)
                    7 -> jibbits8.setImageResource(R.drawable.jibbits_paeng_8)
                    8 -> jibbits8.setImageResource(R.drawable.jibbits_bear_8)
                    9 -> jibbits8.setImageResource(R.drawable.jibbits_octopus_8)
                    10 -> jibbits8.setImageResource(R.drawable.jibbits_strawberry_8)
                }

                when(Auth.locationdata.value?.location_9){
                    0 -> jibbits9.visibility = View.GONE
                    1 -> jibbits9.setImageResource(R.drawable.jibbits_bee_9)
                    2 -> jibbits9.setImageResource(R.drawable.jibbits_duck_9)
                    3 -> jibbits9.setImageResource(R.drawable.jibbits_cat_9)
                    4 -> jibbits9.setImageResource(R.drawable.jibbits_clover_9)
                    5 -> jibbits9.setImageResource(R.drawable.jibbits_flower_9)
                    6 -> jibbits9.setImageResource(R.drawable.jibbits_orange_9)
                    7 -> jibbits9.setImageResource(R.drawable.jibbits_paeng_9)
                    8 -> jibbits9.setImageResource(R.drawable.jibbits_bear_9)
                    9 -> jibbits9.setImageResource(R.drawable.jibbits_octopus_9)
                    10 -> jibbits9.setImageResource(R.drawable.jibbits_strawberry_9)
                }

                when(Auth.locationdata.value?.location_10){
                    0 -> jibbits10.visibility = View.GONE
                    1 -> jibbits10.setImageResource(R.drawable.jibbits_bee_10)
                    2 -> jibbits10.setImageResource(R.drawable.jibbits_duck_10)
                    3 -> jibbits10.setImageResource(R.drawable.jibbits_cat_10)
                    4 -> jibbits10.setImageResource(R.drawable.jibbits_clover_10)
                    5 -> jibbits10.setImageResource(R.drawable.jibbits_flower_10)
                    6 -> jibbits10.setImageResource(R.drawable.jibbits_orange_10)
                    7 -> jibbits10.setImageResource(R.drawable.jibbits_paeng_10)
                    8 -> jibbits10.setImageResource(R.drawable.jibbits_bear_10)
                    9 -> jibbits10.setImageResource(R.drawable.jibbits_octopus_10)
                    10 -> jibbits10.setImageResource(R.drawable.jibbits_strawberry_10)
                }
            }
        }


        //LiveData를 통한 데이터 변경을 감지후 프레그먼트 갱신
        Auth.loginFlag.observe(viewLifecycleOwner) { flag ->
            binding.HomeUserTitleText.text = Auth.user_name.value
            val imageView :ImageView = binding.root.findViewById(R.id.HomeProfileImage)
            Picasso.get()
                .load(profileImage)
                .transform(CircleTransformation())
                .into(imageView)

            binding.HomeTitleText.text = randText() //상단문구 변경

            //지비츠 아이템 세팅
            setjibbits()

            if(total_item_cnt.value != 0)//지비츠를 보유중인 경우
            {
                val recyclerView = binding.HomeMyJibbitsItemRecyclerView
                val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                val box :ImageView = binding.HomeMyJibbitsBox
                box.visibility = View.INVISIBLE

                recyclerView.layoutManager = layoutManager

                //val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
                recyclerView.addItemDecoration(JibbitsAdapter.ItemSpacingDecoration(8))

                val myAllJibbitsAdapter = MyAllJibbitsAdapter(requireContext())
                recyclerView.adapter = myAllJibbitsAdapter


                for(item in itemList)
                {
                    val itemCount = item.item_cnt

                    for(i in 0 until itemCount)//지비츠 정보 세팅
                    {
                        when (item.item_id) {
                            1 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_bee", "행복한꿀벌"))
                            2 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_duck", "아기오리"))
                            3 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_cat", "고양이"))
                            4 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_clover", "네잎클로버"))
                            5 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_flower", "보라색 꽃"))
                            6 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_orange", "오렌지"))
                            7 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_paeng", "펭귄"))
                            8 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_bear", "분홍곰"))
                            9 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_octopus", "문어"))
                            10 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_strawberry", "딸기"))
                        }
                    }

                    myAllJibbitsAdapter.notifyDataSetChanged()
                }
            }
            else
            {
                val box :ImageView = binding.HomeMyJibbitsBox
                box.visibility = View.VISIBLE
            }
        }

        //리사이클러뷰 변경사항 감지후 반영함.
        total_item_cnt.observe(viewLifecycleOwner) {itemCnt ->

            val recyclerView = binding.HomeMyJibbitsItemRecyclerView
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val box :ImageView = binding.HomeMyJibbitsBox

            if(total_item_cnt.value != 0)
                box.visibility = View.INVISIBLE

            recyclerView.layoutManager = layoutManager

            recyclerView.addItemDecoration(JibbitsAdapter.ItemSpacingDecoration(8))

            val myAllJibbitsAdapter = MyAllJibbitsAdapter(requireContext())
            recyclerView.adapter = myAllJibbitsAdapter


            for(item in itemList)
            {
                val itemCount = item.item_cnt

                for(i in 0 until itemCount)//지비츠 정보 세팅
                {
                    when (item.item_id) {
                        1 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_bee", "행복한꿀벌"))
                        2 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_duck", "아기오리"))
                        3 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_cat", "고양이"))
                        4 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_clover", "네잎클로버"))
                        5 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_flower", "보라색 꽃"))
                        6 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_orange", "오렌지"))
                        7 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_paeng", "펭귄"))
                        8 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_bear", "분홍곰"))
                        9 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_octopus", "문어"))
                        10 -> myAllJibbitsAdapter.data.add(MyAllJibbitsData(item.item_id, "jibbits_strawberry", "딸기"))
                    }
                }

                myAllJibbitsAdapter.notifyDataSetChanged()
            }

        }



        Auth.changeFlag.observe(viewLifecycleOwner) {
            setjibbits()
            binding.HomeTitleText.text = randText() //상단문구 변경
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun randText() :String
    {
        val num = Random().nextInt(4)
        var Text = ""
        when (num){
            0 -> Text = "오늘도 열심히 걸어볼까요?"
            1 -> Text = "걷기 운동으로 기분전환 해볼까요?"
            2 -> Text = "환영합니다."
            3 -> Text = "오늘도 운동하는 당신이 아름답습니다."
        }

        return Text
    }
}