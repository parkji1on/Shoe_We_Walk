package com.example.shoe_we_walk.Retrofit

import com.example.shoe_we_walk.Util.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private var gson = GsonBuilder().setLenient().create()

    private val retrofitClient: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient())
            .build()
    }

    val getRetrofitService: RetrofitInterface by lazy { retrofitClient.create(RetrofitInterface::class.java) }
}

fun okHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()    //객체 생성
    val logging = HttpLoggingInterceptor()  //client-okhttp-server 통신을 제어, 서로의 로그를 찍기 위한 interceptor
    logging.level = HttpLoggingInterceptor.Level.BODY   //log의 레벨을 결정함 - BODY: body만 찍음
    return builder.addInterceptor(logging).build()  //생성한 객체 반환
}

/*
fun getRetorfit(): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient())
        .build()

    return retrofit
}*/
