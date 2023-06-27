package com.example.shoe_we_walk.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Auth {
    var isLogin: Boolean = false
    var loginflag = false

    private val _loginFlag: MutableLiveData<Boolean> = MutableLiveData()
    val loginFlag: LiveData<Boolean> = _loginFlag

    // loginflag 값 설정하는 메서드
    fun setLoginFlag(flag: Boolean) {
        loginflag = flag
        _loginFlag.value = flag
    }



    private val _changeFlag: MutableLiveData<Boolean> = MutableLiveData()
    val changeFlag: LiveData<Boolean> = _changeFlag

    fun setchangeFlag(){
        _changeFlag.value = changeFlag.value != true
    }



    //    info
    var profileImage :String? = null
    var user_id :Long = 0

    private val _user_name: MutableLiveData<String> = MutableLiveData("")
    val user_name: LiveData<String> = _user_name

    fun setUserName(userName: String) {
        _user_name.value = userName
    }

    var nickname :String = ""


    private val _age: MutableLiveData<Int> = MutableLiveData(20)
    val age: LiveData<Int> = _age

    fun setAge(Age: Int) {
        _age.value = Age
    }


    private val _height: MutableLiveData<Int> = MutableLiveData(180)
    val height: LiveData<Int> = _height

    fun setHeight(Height: Int) {
        _height.value = Height
    }


    private val _weight: MutableLiveData<Double> = MutableLiveData(70.9)
    val weight: LiveData<Double> = _weight

    fun setWeight(Weight: Double) {
        _weight.value = Weight
    }


    private val _gender: MutableLiveData<String> = MutableLiveData("male")
    val gender: LiveData<String> = _gender

    fun setGender(Gender: String) {
        _gender.value = Gender
    }


    private val _coin: MutableLiveData<Int> = MutableLiveData(0)
    val coin: LiveData<Int> = _coin

    private val _totalItemCnt: MutableLiveData<Int> = MutableLiveData(0)
    val total_item_cnt: LiveData<Int> = _totalItemCnt
    fun setCoin(coinValue: Int) {
        _coin.value = coinValue
    }

    fun setTotalItemCnt(itemCnt: Int) {
        _totalItemCnt.value = itemCnt
    }

//    item info
    var itemList :MutableList<ItemTable> = mutableListOf()
    // List에 아이템 추가하는 함수
    fun addItem(item: ItemTable) {
        itemList.add(item)
    }

    // List에서 아이템 제거하는 함수
    fun removeItem(item: ItemTable) {
        itemList.remove(item)
    }

    // List의 모든 아이템을 가져오는 함수
    fun getAllItems(): List<ItemTable> {
        return itemList
    }


//    Position info
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

    private val locationData: MutableLiveData<LocationData> = MutableLiveData<LocationData>().apply {
        value = LocationData()
    }
    val locationdata :LiveData<LocationData> = locationData
}

//모든 인증정보를 가지고 있음
//isLogin -> login 되었는지
//info -> 자신의 정보
//     -> 착용한 아이템 정보