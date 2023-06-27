package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shoe_we_walk.Data.*
import com.example.shoe_we_walk.Retrofit.RetrofitClient
import com.example.shoe_we_walk.Retrofit.errormessage
import com.example.shoe_we_walk.Retrofit.failuremessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nametext : EditText = findViewById(R.id.EditProfileUserNameEditText)
        val gendertext : TextView = findViewById(R.id.EditProfileUserGenderText)
        val agetext : EditText = findViewById(R.id.EditProfileUserAgeEditText)
        val hightedittext : EditText = findViewById(R.id.EditProfileUserHightEditText)
        val weightedittext : EditText = findViewById(R.id.EditProfileUserWeightEditText)

        var genderflag :Boolean
        genderflag = Auth.gender.value == "male" //male이면 true값
        gendertext.setOnClickListener {
            if(genderflag)
            {
                genderflag = false
                gendertext.text = "여성"
            }
            else
            {
                genderflag = true
                gendertext.text = "남성"
            }
        }

        val done :ImageView = findViewById(R.id.EditProfileEditImage)


        done.setOnClickListener {
            if (nametext.length() == 0 || agetext.length() == 0 || hightedittext.length() == 0 || weightedittext.length() == 0) {
                Toast.makeText(this, "비어있는 항목이 있습니다.\n모두 작성해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Auth.setUserName(nametext.text.toString())

                if(genderflag)
                    Auth.setGender("male")
                else
                    Auth.setGender("female")

                Auth.setAge(agetext.text.toString().toInt())

                Auth.setHeight(hightedittext.text.toString().toInt())

                Auth.setWeight(weightedittext.text.toString().toDouble())

                registUser(UserRegisterRequest(Auth.user_id, Auth.user_name.value!!, Auth.gender.value!!,
                    Auth.age.value!!, Auth.height.value!!, Auth.weight.value!!))
            }
        }
    }

    private fun registUser(userRegisterRequest : UserRegisterRequest){
        RetrofitClient.getRetrofitService.registUser(userRegisterRequest).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if(response.isSuccessful()){
                    if(response.code() == 200) {
                        Toast.makeText(this@RegisterActivity, "가입을 완료했습니다.", Toast.LENGTH_SHORT).show()
                        getUser(Auth.user_id)
                        finish()
                    }
                }
                else
                {
                    Toast.makeText(this@RegisterActivity, errormessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, failuremessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    //유저정보 가져오기
    private fun getUser(user_id : Long){
        RetrofitClient.getRetrofitService.getUser(user_id).enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                if(response.isSuccessful()){
                    Auth.setUserName(response.body()!!.name)
                    Auth.setGender(response.body()!!.gender)
                    Auth.setAge(response.body()!!.age)
                    Auth.setHeight(response.body()!!.height)
                    Auth.setWeight(response.body()!!.weight)
                    Auth.setTotalItemCnt(response.body()!!.total_item)
                    Auth.setCoin(response.body()!!.balance)

                    getItemLoc(Auth.user_id)
                }
                else
                {

                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {

            }
        })
    }

    //아이템 위치값 가져오기
    private fun getItemLoc(user_id : Long){
        RetrofitClient.getRetrofitService.getItemLoc(user_id).enqueue(object : Callback<ItemLocDataResponse> {
            override fun onResponse(call: Call<ItemLocDataResponse>,response: Response<ItemLocDataResponse>) {
                if(response.isSuccessful()){
                    Auth.locationdata.value!!.location_1 = response.body()!!.loc_1
                    Auth.locationdata.value!!.location_2 = response.body()!!.loc_2
                    Auth.locationdata.value!!.location_3 = response.body()!!.loc_3
                    Auth.locationdata.value!!.location_4 = response.body()!!.loc_4
                    Auth.locationdata.value!!.location_5 = response.body()!!.loc_5
                    Auth.locationdata.value!!.location_6 = response.body()!!.loc_6
                    Auth.locationdata.value!!.location_7 = response.body()!!.loc_7
                    Auth.locationdata.value!!.location_8 = response.body()!!.loc_8
                    Auth.locationdata.value!!.location_9 = response.body()!!.loc_9
                    Auth.locationdata.value!!.location_10 = response.body()!!.loc_10

                    getAllItem(Auth.user_id)
                }
                else
                {

                }
            }

            override fun onFailure(call: Call<ItemLocDataResponse>, t: Throwable) {

            }
        })
    }



    //아이템정보 다 가져오기 (종류별 아이템 개수)
    private fun getAllItem(user_id : Long){
        RetrofitClient.getRetrofitService.getAllItem(user_id).enqueue(object : Callback<List<ItemDataResponse>> {
            override fun onResponse(call: Call<List<ItemDataResponse>>,response: Response<List<ItemDataResponse>>) {
                if(response.isSuccessful()){
                    val itemList: List<ItemDataResponse>? = response.body()
                    if (itemList != null) {
                        for (item in itemList) {
                            Auth.addItem(ItemTable(item.user_id, item.item_id, item.item_cnt))
                        }
                    }
                }
                else
                {

                }
            }

            override fun onFailure(call: Call<List<ItemDataResponse>>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, failuremessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}