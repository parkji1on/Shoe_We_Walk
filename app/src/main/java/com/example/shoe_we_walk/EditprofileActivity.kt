package com.example.shoe_we_walk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.Data.MessageResponse
import com.example.shoe_we_walk.Data.UserRegisterRequest
import com.example.shoe_we_walk.Retrofit.RetrofitClient
import com.example.shoe_we_walk.Util.CircleTransformation
import com.example.shoe_we_walk.Util.errormessage
import com.example.shoe_we_walk.Util.failuremessage
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditprofileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)


        val titlename : TextView = findViewById(R.id.EditProfileUserTitleText)
        titlename.text = nickname
        val imageView : ImageView = findViewById(R.id.EditProfileUserImage)
        Picasso.get()
            .load(profileImage)
            .transform(CircleTransformation())
            .into(imageView)
        val nametext :EditText = findViewById(R.id.EditProfileUserNameEditText)
        val gendertext :TextView = findViewById(R.id.EditProfileUserGenderText)
        val agetext :EditText = findViewById(R.id.EditProfileUserAgeEditText)
        val hightedittext :EditText = findViewById(R.id.EditProfileUserHightEditText)
        val weightedittext :EditText = findViewById(R.id.EditProfileUserWeightEditText)

        nametext.hint = Auth.user_name.value
        agetext.hint = Auth.age.value.toString()
        hightedittext.hint = Auth.height.value.toString()
        weightedittext.hint = Auth.weight.value.toString()

        var genderflag :Boolean
        genderflag = Auth.gender.value == "male" //male이면 true값
        if(genderflag)
            gendertext.text = "남성"
        else
            gendertext.text = "여성"

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



        val donebtn :ImageView = findViewById(R.id.EditProfileEditImage)
        donebtn.setOnClickListener {
            if(nametext.length() != 0)
                Auth.setUserName(nametext.text.toString())

            if((genderflag && (Auth.gender.value == "female")) || (!genderflag && (Auth.gender.value == "male")))
            {
                if(genderflag)
                    Auth.setGender("male")
                else
                    Auth.setGender("female")
            }

            if(agetext.length() != 0)
                Auth.setAge(agetext.text.toString().toInt())

            if(hightedittext.length() != 0)
                Auth.setHeight(hightedittext.text.toString().toInt())

            if(weightedittext.length() != 0)
                Auth.setWeight(weightedittext.text.toString().toDouble())

            updateUser(UserRegisterRequest(Auth.user_id, Auth.user_name.value!!, Auth.gender.value!!, Auth.age.value!!, Auth.height.value!!, Auth.weight.value!!))

        }


    }

    //유저정보 업데이트
    private fun updateUser(userRegisterRequest : UserRegisterRequest){
        RetrofitClient.getRetrofitService.updateUser(userRegisterRequest).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
                if(response.isSuccessful){
                    if(response.code() == 200)
                    {
                        Toast.makeText(this@EditprofileActivity, "프로필정보가 업데이트 되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@EditprofileActivity, errormessage, Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this@EditprofileActivity, errormessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Toast.makeText(this@EditprofileActivity, failuremessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

}