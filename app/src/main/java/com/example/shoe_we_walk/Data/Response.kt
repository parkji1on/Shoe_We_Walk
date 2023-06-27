package com.example.shoe_we_walk.Data

data class UserIdResponse(
    var user_id : Int
)

data class UserDataResponse(
    var user_id : Int,
    var name : String,
    var nickname : String,
    var gender : String,
    var age : Int,
    var height : Int,
    var weight : Float
)

data class WorkDataResponse(
    var user_id : Int,
    var work_date : String,		//pattern = "yyyy-MM-dd HH:mm:ss"
    var step_num : Int,
    var work_dist : Float,
    var calories : Int
)

data class ItemDataResponse(
    var user_id : Long,
    var item_id : Int,
    var item_cnt : Int,		//유저가 가지고 있는 해당 item의 총 개수
    var price : Int
)


data class MessageResponse(
    var message : String
)

data class ItemTable(
    var user_id :Long,
    var item_id :Int,
    var item_cnt :Int
)

data class ItemLocationResponse(
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

data class locationTable(
    var loc_1 :Int,
    var loc_2 :Int,
    var loc_3 :Int,
    var loc_4 :Int,
    var loc_5 :Int,
    var loc_6 :Int,
    var loc_7 :Int,
    var loc_8 :Int,
    var loc_9 :Int,
    var loc_10 :Int
)