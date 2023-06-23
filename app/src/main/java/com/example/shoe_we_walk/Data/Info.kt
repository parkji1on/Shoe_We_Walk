package com.example.shoe_we_walk.Data

data class Info(
    var user_id: Int,
    var name : String,
    var nickname : String,
    var gender : String,
    var age : Int,
    var height : Int,
    var weight : Float
)

data class Work(
    var work_date : String,		//pattern = "yyyy-MM-dd HH:mm:ss"
    var work_time : Int,        //운동한 시간
    var step_num : Int,         //총 걸음수
    var work_dist : Float,      //운동한 거리
    var calories : Int          //총 칼로리
)