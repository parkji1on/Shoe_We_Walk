package com.example.shoe_we_walk.Data

import com.github.mikephil.charting.data.BarEntry

data class UserIdResponse(
    var user_id : Int
)

data class UserDataResponse(
    var user_id : Long,
    var name : String,
    var gender : String,
    var age : Int,
    var height : Int,
    var weight : Double,
    var total_item : Int,
    var balance : Int
)

data class WorkDataResponse(
    var user_id : Long,
    var work_date : String,		//pattern = "yyyy-MM-dd HH:mm:ss"
    var step_num : Int,
    var work_dist : Float,
    var calories : Int
)

data class ItemDataResponse(
    var user_id : Long,
    var item_id : Int,
    var item_cnt : Int,
    var price : Int
)

data class ItemLocDataResponse(
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

data class WeekStepNum(
    var day1 : Int,
    var day2 : Int,
    var day3 : Int,
    var day4 : Int,
    var day5 : Int,
    var day6 : Int,
    var day7 : Int
){
    fun changeData(): ArrayList<BarEntry>{
        return arrayListOf(
            BarEntry(1.0f, day1.toFloat()),
            BarEntry(2.0f, day2.toFloat()),
            BarEntry(3.0f, day3.toFloat()),
            BarEntry(4.0f, day4.toFloat()),
            BarEntry(5.0f, day5.toFloat()),
            BarEntry(6.0f, day6.toFloat()),
            BarEntry(7.0f, day7.toFloat())
        )
    }
}

data class MonthStepNum(
    var week1 : Int,
    var week2 : Int,
    var week3 : Int,
    var week4 : Int,
    var week5 : Int
){
    fun changeData(): ArrayList<BarEntry>{
        return arrayListOf(
            BarEntry(1.0f, week1.toFloat()),
            BarEntry(2.0f, week2.toFloat()),
            BarEntry(3.0f, week3.toFloat()),
            BarEntry(4.0f, week4.toFloat()),
            BarEntry(5.0f, week5.toFloat())
        )
    }
}

data class YearStepNum(
    var month1 : Int,
    var month2 : Int,
    var month3 : Int,
    var month4 : Int,
    var month5 : Int,
    var month6 : Int,
    var month7 : Int,
    var month8 : Int,
    var month9 : Int,
    var month10 : Int,
    var month11 : Int,
    var month12 : Int
){
    fun changeData(): ArrayList<BarEntry>{
        return arrayListOf(
            BarEntry(1.0f, month1.toFloat()),
            BarEntry(2.0f, month2.toFloat()),
            BarEntry(3.0f, month3.toFloat()),
            BarEntry(4.0f, month4.toFloat()),
            BarEntry(5.0f, month5.toFloat()),
            BarEntry(6.0f, month6.toFloat()),
            BarEntry(7.0f, month7.toFloat()),
            BarEntry(8.0f, month8.toFloat()),
            BarEntry(9.0f, month9.toFloat()),
            BarEntry(10.0f, month10.toFloat()),
            BarEntry(11.0f, month11.toFloat()),
            BarEntry(12.0f, month12.toFloat())
        )
    }
}

data class MessageResponse(
    var message : String
)