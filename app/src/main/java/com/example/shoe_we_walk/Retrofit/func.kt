package com.example.shoe_we_walk.Retrofit

import android.util.Log
import com.example.shoe_we_walk.Data.*
import com.example.shoe_we_walk.Retrofit.RetrofitClient.getRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//유저가 등록되어있는지 확인
fun isUserExist(user_id : Long){
    getRetrofitService.isUserExist(user_id).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//유저 등록하기
fun registUser(userRegisterRequest : UserRegisterRequest){
    getRetrofitService.registUser(userRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//유저정보 가져오기
fun getUser(user_id : Long){
    getRetrofitService.getUser(user_id).enqueue(object : Callback<UserDataResponse> {
        override fun onResponse(call: Call<UserDataResponse>,response: Response<UserDataResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//유저정보 업데이트
fun updateUser(userRegisterRequest : UserRegisterRequest){
    getRetrofitService.updateUser(userRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//아이템 위치값 가져오기
fun getItemLoc(user_id : Long){
    getRetrofitService.getItemLoc(user_id).enqueue(object : Callback<ItemLocDataResponse> {
        override fun onResponse(call: Call<ItemLocDataResponse>,response: Response<ItemLocDataResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<ItemLocDataResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//아이템 위치값 업데이트
fun updateItemLoc(itemLocRegisterRequest : ItemLocRegisterRequest){
    getRetrofitService.updateItemLoc(itemLocRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//아이템정보 가져오기 (아이템 개수)
fun getItem(user_id : Long, item_id : Int){
    getRetrofitService.getItem(user_id, item_id).enqueue(object : Callback<ItemDataResponse> {
        override fun onResponse(call: Call<ItemDataResponse>,response: Response<ItemDataResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<ItemDataResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//아이템정보 다 가져오기 (종류별 아이템 개수)
fun getAllItem(user_id : Long){
    getRetrofitService.getAllItem(user_id).enqueue(object : Callback<List<ItemDataResponse>> {
        override fun onResponse(call: Call<List<ItemDataResponse>>,response: Response<List<ItemDataResponse>>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<List<ItemDataResponse>>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//아이템 구매했을 때 업데이트 요청
fun updateItem(user_id : Long, item_id : Int, add : Int){
    getRetrofitService.updateItem(user_id, item_id, add).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//work 정보 등록하기
fun registWork(workRegisterRequest : WorkRegisterRequest){
    getRetrofitService.registWork(workRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//work 정보 가져오기
fun getWork(user_id : Long, work_date1 : String, work_date2 : String){
    getRetrofitService.getWork(user_id, work_date1, work_date2).enqueue(object : Callback<List<WorkDataResponse>> {
        override fun onResponse(call: Call<List<WorkDataResponse>>,response: Response<List<WorkDataResponse>>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<List<WorkDataResponse>>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//현재 날짜에 해당하는 week의 day별 걸음수 가져오기
fun getWeekWork(user_id : Long, completion: (WeekStepNum, String)->Unit){
    getRetrofitService.getWeekWork(user_id).enqueue(object : Callback<WeekStepNum> {
        override fun onResponse(call: Call<WeekStepNum>,response: Response<WeekStepNum>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
                Log.d("Response:", response.toString())
                completion(response.body() as WeekStepNum, response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
                completion(WeekStepNum(0,0,0,0,0,0,0), response.errorBody().toString())
            }
        }

        override fun onFailure(call: Call<WeekStepNum>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
            completion(WeekStepNum(0,0,0,0,0,0,0), t.toString())
        }
    })
}

//현재 날짜에 해당하는 month의 week별 걸음수 가져오기
fun getMonthWork(user_id : Long){
    getRetrofitService.getMonthWork(user_id).enqueue(object : Callback<MonthStepNum> {
        override fun onResponse(call: Call<MonthStepNum>,response: Response<MonthStepNum>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<MonthStepNum>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}

//현재 날짜에 해당하는 year의 month별 걸음수 가져오기
fun getYearWork(user_id : Long){
    getRetrofitService.getYearWork(user_id).enqueue(object : Callback<YearStepNum> {
        override fun onResponse(call: Call<YearStepNum>,response: Response<YearStepNum>) {
            if(response.isSuccessful()){
                Log.d("Response:", response.body().toString())
            }
            else
            {
                Log.d("Response FAILURE", response.errorBody()!!.string())
            }
        }

        override fun onFailure(call: Call<YearStepNum>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}
