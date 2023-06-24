package com.example.shoe_we_walk.Data

object Auth {
    var isLogin: Boolean = false

    lateinit var info: Info
}
//모든 인증정보를 가지고 있음
//isLogin -> login 되었는지
//info -> 자신의 정보
//     -> 착용한 아이템 정보