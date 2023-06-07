package com.example.shoe_we_walk.Util

import android.icu.text.SimpleDateFormat
import java.lang.Math.*
import kotlin.math.pow

const val R = 6372.8 * 1000

// gps to Distance(m 단위)
fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Int {
    val dLat = toRadians(lat2 - lat1)
    val dLon = toRadians(lon2 - lon1)
    val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(toRadians(lat1)) * cos(toRadians(lat2))
    val c = 2 * asin(sqrt(a))
    return (R * c).toInt()
}
