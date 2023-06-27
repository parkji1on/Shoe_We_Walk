package com.example.shoe_we_walk

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.shoe_we_walk.Data.Auth.loginflag
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.Data.Auth.setLoginFlag
import com.example.shoe_we_walk.Data.Auth.user_id
import com.example.shoe_we_walk.Data.MessageResponse
import com.example.shoe_we_walk.Retrofit.RetrofitClient
import com.example.shoe_we_walk.Util.APP_KEY
import com.example.shoe_we_walk.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패 $error")
        } else if (token != null) {
            Log.d(TAG, "로그인 성공 ${token.accessToken}")


            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패 $error")
                } else if (user != null) {
                    Log.e(TAG, "사용자 정보 요청 성공 : $user")
                    nickname = user.kakaoAccount?.profile?.nickname.toString()
                    /*binding.txtAge.text = user.kakaoAccount?.ageRange.toString()
                    binding.txtEmail.text = user.kakaoAccount?.email*/
                    profileImage = user.kakaoAccount?.profile?.profileImageUrl
                }
            }
            nextMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Toast.makeText(this, "ASD", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")

        KakaoSdk.init(this, APP_KEY)
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error == null) {
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 정보 요청 실패 $error")
                        } else if (user != null) {
                            Log.e(TAG, "사용자 정보 요청 성공 : $user")
                            user_id = user.id!!
                            nickname = user.kakaoAccount?.profile?.nickname.toString()
                            profileImage = user.kakaoAccount?.profile?.profileImageUrl //이게진짜
                            setLoginFlag(true)
                            isUserExist(user_id)
                        }
                    }
                    nextMainActivity()
                }
            }
        }

        setContentView(binding.root)

        binding.LoginKaKao.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.LoginKaKao.id -> {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                    UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "로그인 실패 $error")
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            } else {
                                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                            }
                        } else if (token != null) {
                            Log.d(TAG, "로그인 성공 ${token.accessToken}")
                            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                            nextMainActivity()
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                }
            }
        }
    }

    private fun nextMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        finish()
    }

    //유저가 등록되어있는지 확인
    private fun isUserExist(user_id : Long){
        RetrofitClient.getRetrofitService.isUserExist(user_id).enqueue(object :
            Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                val Code = response.code()
                when(Code){
                    200 -> { //user_id 값이 DB에 저장되어 있지 않을 때
                        Toast.makeText(this@LoginActivity, "추가정보를 입력하여 가입을 완료해주세요.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                    409 -> { //user_id 값이 이미 DB에 저장되어 있을 때
                        Toast.makeText(this@LoginActivity, "환영합니다.", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {

            }
        })
    }

}
