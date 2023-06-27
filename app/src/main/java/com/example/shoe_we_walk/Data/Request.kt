package com.example.shoe_we_walk.Data

data class UserRegisterRequest(
    var user_id : Long,
    var name : String,
    var gender : String,
    var age : Int,
    var height : Int,
    var weight : Double
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


data class ItemLocRegisterRequest(
    var user_id : Long,
    var loc_1 : Int,
    var loc_2 : Int,
    var loc_3 : Int,
    var loc_4 : Int,
    var loc_5 : Int,
    var loc_6 : Int,
    var loc_7 : Int,
    var loc_8 : Int,
    var loc_9 : Int,
    var loc_10 : Int
)