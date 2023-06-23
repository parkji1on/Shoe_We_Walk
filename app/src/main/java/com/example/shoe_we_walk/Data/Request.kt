package com.example.shoe_we_walk.Data

data class UserRegisterRequest(
    var name : String,
    var nickname : String,
    var gender : String,
    var age : Int,
    var height : Int,
    var weight : Float
)

data class WorkRegisterRequest(
    var work_date : String,		//pattern = "yyyy-MM-dd HH:mm:ss"
    var step_num : Int,
    var work_dist : Float,
    var calories : Int
)

data class ItemRegisterRequest(
    var item_id : Int,
    var item_loc : Int,
    var is_attached : Int
)
