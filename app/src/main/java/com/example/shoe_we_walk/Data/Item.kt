package com.example.shoe_we_walk.Data

data class LocationData(
    var location_1: Int = 0,
    var location_2: Int = 0,
    var location_3: Int = 0,
    var location_4: Int = 0,
    var location_5: Int = 0,
    var location_6: Int = 0,
    var location_7: Int = 0,
    var location_8: Int = 0,
    var location_9: Int = 0,
    var location_10: Int = 0
)

data class ItemTable(
    var user_id :Long,
    var item_id :Int,
    var item_cnt :Int
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