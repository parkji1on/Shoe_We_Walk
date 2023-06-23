package com.example.shoe_we_walk.Retrofit

import android.util.Log
import com.example.shoe_we_walk.Data.*
import com.example.shoe_we_walk.Retrofit.RetrofitClient.getRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//유저 등록하기
fun insertUser(userRegisterRequest : UserRegisterRequest){
    getRetrofitService.insertUser(userRegisterRequest).enqueue(object : Callback<UserIdResponse> {
        override fun onResponse(call: Call<UserIdResponse>, response: Response<UserIdResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body()?.user_id.toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<UserIdResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//아이템 등록하기
fun insertItem(user_id : Int, itemRegisterRequest : ItemRegisterRequest){
    getRetrofitService.insertItem(user_id, itemRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//운동기록 등록하기
fun insertWork(user_id : Int, workRegisterRequest : WorkRegisterRequest){
    getRetrofitService.insertWork(user_id, workRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//유저정보 요청하기
fun selectUser(user_id : Int){
    getRetrofitService.selectUser(user_id).enqueue(object : Callback<UserDataResponse> {
        override fun onResponse(call: Call<UserDataResponse>,response: Response<UserDataResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//아이템 정보 가져오기
 fun selectItem(user_id : Int, item_id : Int){
    getRetrofitService.selectItem(user_id, item_id).enqueue(object : Callback<ItemDataResponse> {
        override fun onResponse(call: Call<ItemDataResponse>,response: Response<ItemDataResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<ItemDataResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//아이템정보 다 가져오기
fun selectAllItem(user_id : Int){
    getRetrofitService.selectAllItem(user_id).enqueue(object : Callback<List<ItemDataResponse>> {
        override fun onResponse(call: Call<List<ItemDataResponse>>,response: Response<List<ItemDataResponse>>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<List<ItemDataResponse>>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//운동정보 가져오기
fun selectWork(user_id : Int, work_date1 : String, work_date2 : String){
    getRetrofitService.selectWork(user_id, work_date1, work_date2).enqueue(object : Callback<List<WorkDataResponse>> {
        override fun onResponse(call: Call<List<WorkDataResponse>>,response: Response<List<WorkDataResponse>>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<List<WorkDataResponse>>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//유저정보 갱신하기
fun updateUser(user_id : Int, userRegisterRequest : UserRegisterRequest){
    getRetrofitService.updateUser(user_id, userRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}

//아이템정보 갱신하기
fun updateItem(user_id : Int, itemRegisterRequest : ItemRegisterRequest){
//    val api=RetroInterface.create()
    getRetrofitService.updateItem(user_id, itemRegisterRequest).enqueue(object : Callback<MessageResponse> {
        override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
            if(response.isSuccessful){
                Log.d("Response:", response.body().toString())
            }
            else
            {
//                Log.d("Response FAILURE", response.errorBody().string());
            }
        }

        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
            Log.d("CONNECTION FAILURE :", t.localizedMessage)
        }
    })
}