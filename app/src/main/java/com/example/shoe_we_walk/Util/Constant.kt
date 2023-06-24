package com.example.shoe_we_walk.Util

import android.icu.text.SimpleDateFormat
import com.example.shoe_we_walk.Data.Info

val TAG: String = "로그"

const val BASE_URL = "http://3.37.186.27:8080/shoe_we_walk/"

object Constant{
    val mFormat = SimpleDateFormat("hh:mm:ss")          //걸린 시간을 나타내는 format
}

val user = Info(
    user_id = 7,
    name = "박지원",
    nickname = "박지원",
    gender = "male",
    age = 18,
    height = 170,
    weight = 68.0f
)
