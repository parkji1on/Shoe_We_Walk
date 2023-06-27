package com.example.shoe_we_walk.Retrofit

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.shoe_we_walk.Data.*
import com.example.shoe_we_walk.EditprofileActivity
import com.example.shoe_we_walk.RegisterActivity
import com.example.shoe_we_walk.Retrofit.RetrofitClient.getRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val failuremessage :String = "서버와 연결에 실패했습니다. 인터넷 연결을 확인해주세요."
val errormessage :String = "요청에 실패했습니다. 다시 시도해주세요."














//아이템정보 갱신하기
/*
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
            Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
        }
    })
}*/
