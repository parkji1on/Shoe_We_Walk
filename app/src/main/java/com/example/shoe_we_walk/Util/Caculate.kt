package com.example.shoe_we_walk.Util

import java.lang.Math.*
import kotlin.math.pow

const val R = 6372.8 * 1000

// gps to Distance(m 단위)
fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val dLat = toRadians(lat2 - lat1)
    val dLon = toRadians(lon2 - lon1)
    val a = kotlin.math.sin(dLat / 2).pow(2.0)
        + kotlin.math.sin(dLon / 2).pow(2.0) * kotlin.math.cos(toRadians(lat1)) * kotlin.math.cos(toRadians(lat2))
    val c = 2 * kotlin.math.asin(kotlin.math.sqrt(a))
    return R * c              //return - m
}

//칼로리 계산(kcal 단위)
fun getCalorie(weight: Double, t: Int): Int{       //weight - kg, t - min
//    MET 계산법을 따름(https://lovefor-you.tistory.com/411)
//    걷기 운동의 경 4MET를 적용
//    MET*(3.5*kg*min) = air(ml)-> 4*3.5*weight*t, air(L)*5 = kcal ->air*5/1000   => weight*t*70/1000
    return ((weight*t*7)/100).toInt()             //return - kcal
}

//ms -> HH:mm:ss
fun getTime(time: Int): Array<Any> {
    val second = time/1000
    val minute = second/60
    val hour = minute/60
    return arrayOf(hour, minute%60, second%60)
}

//운동으로 획든한 총 코인 계산
fun getCoin(step: Int): Int{
    return step/1000
}
