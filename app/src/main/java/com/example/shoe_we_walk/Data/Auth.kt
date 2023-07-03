package com.example.shoe_we_walk.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Auth {
    var loginflag = false
    //    info
    var profileImage :String? = null
    var user_id :Long = 0
    var nickname :String = ""

    private val _loginFlag: MutableLiveData<Boolean> = MutableLiveData()
    val loginFlag: LiveData<Boolean> = _loginFlag
    private val _changeFlag: MutableLiveData<Boolean> = MutableLiveData()
    val changeFlag: LiveData<Boolean> = _changeFlag
    private val _user_name: MutableLiveData<String> = MutableLiveData("홍길동")
    val user_name: LiveData<String> = _user_name
    private val _age: MutableLiveData<Int> = MutableLiveData(20)
    val age: LiveData<Int> = _age
    private val _height: MutableLiveData<Int> = MutableLiveData(180)
    val height: LiveData<Int> = _height
    private val _weight: MutableLiveData<Double> = MutableLiveData(70.9)
    val weight: LiveData<Double> = _weight
    private val _gender: MutableLiveData<String> = MutableLiveData("male")
    val gender: LiveData<String> = _gender
    private val _coin: MutableLiveData<Int> = MutableLiveData(0)
    val coin: LiveData<Int> = _coin
    private val _totalItemCnt: MutableLiveData<Int> = MutableLiveData(0)
    val total_item_cnt: LiveData<Int> = _totalItemCnt
    //    item info
    var itemList :MutableList<ItemTable> = mutableListOf()
    //    Position info
    val locationData: MutableLiveData<LocationData> = MutableLiveData<LocationData>().apply {
        value = LocationData()
    }
    val locationdata :LiveData<LocationData> = locationData

    // loginflag 값 설정하는 메서드
    fun setLoginFlag(flag: Boolean) {
        loginflag = flag
        _loginFlag.value = flag
    }

    fun setchangeFlag(){
        _changeFlag.value = changeFlag.value != true
    }

    fun setUserName(userName: String) {
        _user_name.value = userName
    }

    fun setAge(Age: Int) {
        _age.value = Age
    }

    fun setHeight(Height: Int) {
        _height.value = Height
    }

    fun setWeight(Weight: Double) {
        _weight.value = Weight
    }

    fun setGender(Gender: String) {
        _gender.value = Gender
    }

    fun setCoin(coinValue: Int) {
        _coin.value = coinValue
    }

    fun setTotalItemCnt(itemCnt: Int) {
        _totalItemCnt.value = itemCnt
    }

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
}