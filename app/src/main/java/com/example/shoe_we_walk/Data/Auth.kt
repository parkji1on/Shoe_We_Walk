package com.example.shoe_we_walk.Data

object Auth {
    var isLogin: Boolean = false
    var loginflag = false

//    info
    var user_name :String = ""
    var nickname :String = ""
    var gender :String = ""
    var age :Int = 0
    var height :Int = 0
    var weight :Float = 0.0F
    var profileImage :String? = null

//    coin
    var coin :Int = 0
    var total_item_cnt :Int = 0
}
//모든 인증정보를 가지고 있음
//isLogin -> login 되었는지
//info -> 자신의 정보
//     -> 착용한 아이템 정보