package com.example.shoe_we_walk.Util

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class MyXAxisFormatter(period: Int) : ValueFormatter() {

    private val days = arrayOf("일","월","화","수","목","금","토")
    private val weeks = arrayOf("1주차", "2주차", "3주차", "4주차", "5주차")
    private val months = arrayOf("1월", "2월", "3월","4월","5월","6월","7월","8월","9월","10월","11월","12월")
    private var period = period

    //    period에 따라서 days, weeks, months를 다르게 표현
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        var period = choice()
        return period.getOrNull(value.toInt()-1) ?: value.toString()
    }

    private fun choice(): Array<String> {
        return when(period){
            1 -> weeks
            2 -> months
            else -> days
        }
    }
}
