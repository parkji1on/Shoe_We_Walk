package com.example.shoe_we_walk

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.shoe_we_walk.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.squareup.picasso.Picasso


/*
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val KAKAO :ImageView = findViewById(R.id.LoginKaKao)
        KAKAO.setOnClickListener {
            Toast.makeText(this, "로그인 시도", Toast.LENGTH_SHORT).show()

            val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
            val mCallback: (OAuthToken?, Throwable?) -> Unit ={ token, error ->
                if (error != null) {
                    Log.e(Constants.TAG, "로그인 실패 $error")
                } else if (token != null) {
                    Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")
                    nextMainActivity()
                }
            }

        }

    }
}*/

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(Constants.TAG, "로그인 실패 $error")
        } else if (token != null) {
            Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")


            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패 $error")
                } else if (user != null) {
                    Log.e(TAG, "사용자 정보 요청 성공 : $user")
                    MainActivity.nickname = user.kakaoAccount?.profile?.nickname.toString()
                    /*binding.txtAge.text = user.kakaoAccount?.ageRange.toString()
                    binding.txtEmail.text = user.kakaoAccount?.email*/
                    MainActivity.profileImage = user.kakaoAccount?.profile?.profileImageUrl
                }
            }
            nextMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Toast.makeText(this, "ASD", Toast.LENGTH_SHORT).show()
        Log.d(Constants.TAG, "keyhash : ${Utility.getKeyHash(this)}")

        KakaoSdk.init(this, Constants.APP_KEY)
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error == null) {
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 정보 요청 실패 $error")
                        } else if (user != null) {
                            Log.e(TAG, "사용자 정보 요청 성공 : $user")
                            MainActivity.nickname = user.kakaoAccount?.profile?.nickname.toString()
                            /*binding.txtAge.text = user.kakaoAccount?.ageRange.toString()
                            binding.txtEmail.text = user.kakaoAccount?.email*/
                            MainActivity.profileImage = user.kakaoAccount?.profile?.profileImageUrl //이게진짜
                            MainActivity.loginflag = true
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
                            Log.e(Constants.TAG, "로그인 실패 $error")
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            } else {
                                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
                            }
                        } else if (token != null) {
                            Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")
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
        finish()
    }

}
