package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.setchangeFlag
import com.example.shoe_we_walk.Data.ItemLocRegisterRequest
import com.example.shoe_we_walk.Data.MessageResponse
import com.example.shoe_we_walk.Retrofit.RetrofitClient
import com.example.shoe_we_walk.Util.errormessage
import com.example.shoe_we_walk.Util.failuremessage
import com.example.shoe_we_walk.adapter.*
import com.example.shoe_we_walk.databinding.ActivityEditshoesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditshoesActivity : AppCompatActivity() {
    private val cntarray: Array<Int> = Array(11) { 0 }

    private lateinit var binding: ActivityEditshoesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditshoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.EditShoesTitleText.text = nickname + "님의 신발"

        //신발 세팅

        //지비츠 아이템 세팅
        val jibbits1 : ImageView = binding.EditShoesJibbitsImage1
        val jibbits2 : ImageView = binding.EditShoesJibbitsImage2
        val jibbits3 : ImageView = binding.EditShoesJibbitsImage3
        val jibbits4 : ImageView = binding.EditShoesJibbitsImage4
        val jibbits5 : ImageView = binding.EditShoesJibbitsImage5
        val jibbits6 : ImageView = binding.EditShoesJibbitsImage6
        val jibbits7 : ImageView = binding.EditShoesJibbitsImage7
        val jibbits8 : ImageView = binding.EditShoesJibbitsImage8
        val jibbits9 : ImageView = binding.EditShoesJibbitsImage9
        val jibbits10 : ImageView = binding.EditShoesJibbitsImage10


        when(Auth.locationdata.value?.location_1){
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

        binding.EditShoesDoneImage.setOnClickListener {
            updateItemLoc(
                ItemLocRegisterRequest(Auth.user_id, Auth.locationdata.value!!.location_1,
                    Auth.locationdata.value!!.location_2, Auth.locationdata.value!!.location_3,
                    Auth.locationdata.value!!.location_4, Auth.locationdata.value!!.location_5,
                    Auth.locationdata.value!!.location_6, Auth.locationdata.value!!.location_7,
                    Auth.locationdata.value!!.location_8, Auth.locationdata.value!!.location_9,
                    Auth.locationdata.value!!.location_10)
            )
        }


        val recyclerView = binding.EditShoesAbleJibbitsRecyclerview
        val recyclerView2 = binding.EditShoesUsedJibbitsRecyclerview

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView2.layoutManager = layoutManager2

        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        recyclerView.addItemDecoration(MyAbleJibbitsAdapter.ItemSpacingDecoration(spacing))
        recyclerView2.addItemDecoration(MyUsedJibbitsAdapter.ItemSpacingDecoration(spacing))

        val myAbleJibbitsAdapter = MyAbleJibbitsAdapter(this)
        val myUsedJibbitsAdapter = MyUsedJibbitsAdapter(this)

        recyclerView.adapter = myAbleJibbitsAdapter
        recyclerView2.adapter = myUsedJibbitsAdapter

        //Used 세팅
        if(Auth.locationdata.value?.location_1 != 0) //1번위치에 지비츠가 있으면
        {   //위치에 저장되어있는 아이템 idx값이랑, 포지션 정보 저장
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_1, 1)
        }
        if(Auth.locationdata.value?.location_2 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_2, 2)
        }
        if(Auth.locationdata.value?.location_3 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_3, 3)
        }
        if(Auth.locationdata.value?.location_4 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_4, 4)
        }
        if(Auth.locationdata.value?.location_5 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_5, 5)
        }
        if(Auth.locationdata.value?.location_6 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_6, 6)
        }
        if(Auth.locationdata.value?.location_7 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_7, 7)
        }
        if(Auth.locationdata.value?.location_8 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_8, 8)
        }
        if(Auth.locationdata.value?.location_9 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_9, 9)
        }
        if(Auth.locationdata.value?.location_10 != 0)
        {
            addUsed(myUsedJibbitsAdapter, Auth.locationdata.value!!.location_10, 10)
        }

        myUsedJibbitsAdapter.notifyDataSetChanged()

        //unused세팅
        var idx = 1
        for(item in Auth.itemList)
        {
            val itemCount = item.item_cnt - cntarray[idx]
            idx++

            for(i in 0 until itemCount)//지비츠 정보 세팅
            {
                when (item.item_id) {
                    1 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_bee", "행복한꿀벌"))
                    2 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_duck", "아기오리"))
                    3 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_cat", "고양이"))
                    4 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_clover", "네잎클로버"))
                    5 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_flower", "보라색 꽃"))
                    6 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_orange", "오렌지"))
                    7 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_paeng", "펭귄"))
                    8 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_bear", "분홍곰"))
                    9 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_octopus", "문어"))
                    10 -> myAbleJibbitsAdapter.data.add(MyAbleJibbitsData(item.item_id, "jibbits_strawberry", "딸기"))
                }
            }

            myAbleJibbitsAdapter.notifyDataSetChanged()
        }
    }

    private fun addUsed(myUsedJibbitsAdapter :MyUsedJibbitsAdapter,idx :Int, position :Int){
        when (idx) {
            1 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_bee", "행복한꿀벌", position))
                cntarray[1]++
            }
            2 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_duck", "아기오리", position))
                cntarray[2]++
            }
            3 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_cat", "고양이",position))
                cntarray[3]++
            }
            4 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_clover", "네잎클로버",position))
                cntarray[4]++
            }
            5 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_flower", "보라색 꽃",position))
                cntarray[5]++
            }
            6 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_orange", "오렌지",position))
                cntarray[6]++
            }
            7 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_paeng", "펭귄",position))
                cntarray[7]++
            }
            8 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_bear", "분홍곰",position))
                cntarray[8]++
            }
            9 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_octopus", "문어",position))
                cntarray[9]++
            }
            10 -> {
                myUsedJibbitsAdapter.data.add(MyUsedJibbitsData(idx, "jibbits_strawberry", "딸기",position))
                cntarray[10]++
            }
        }

        fun reloadActivity() {
            val intent = intent
            finish()
            startActivity(intent)
        }
    }

    //아이템 위치값 업데이트
    private fun updateItemLoc(itemLocRegisterRequest : ItemLocRegisterRequest){
        RetrofitClient.getRetrofitService.updateItemLoc(itemLocRegisterRequest).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
                if(response.isSuccessful){
                    if(response.code() == 200)
                    {
                        Toast.makeText(this@EditshoesActivity, "신발 정보가 업데이트 되었습니다.", Toast.LENGTH_SHORT).show()
                        setchangeFlag()
                        finish()
                    }
                    else
                        Toast.makeText(this@EditshoesActivity, errormessage, Toast.LENGTH_SHORT).show()

                }
                else
                {
                    Toast.makeText(this@EditshoesActivity, errormessage, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Toast.makeText(this@EditshoesActivity, failuremessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}