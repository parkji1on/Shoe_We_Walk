package com.example.shoe_we_walk.Util

import com.example.shoe_we_walk.Data.Info

const val APP_KEY = "a7d658be72e6d8d57c12f265b829d759"
const val BASE_URL = "http://3.37.186.27:8080/shoe_we_walk2/"
const val failuremessage :String = "서버와 연결에 실패했습니다. 인터넷 연결을 확인해주세요."
const val errormessage :String = "요청에 실패했습니다. 다시 시도해주세요."

val user = Info(
    user_id = 1,
    name = "박지원",
    gender = "male",
    age = 18,
    height = 170,
    weight = 68.0,
    total_item = 3,
    balance = 100
)

