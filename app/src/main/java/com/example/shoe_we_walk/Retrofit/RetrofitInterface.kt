package com.example.shoe_we_walk.Retrofit

import com.example.shoe_we_walk.Data.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("api/exist/user")
    fun isUserExist(
        @Query("user_id") user_id : Long
    ) : Call<MessageResponse>

    @POST("api/regist/user")
    fun registUser(
        @Body jsonparms : UserRegisterRequest
    ) : Call<MessageResponse>

    @GET("api/get/user")
    fun getUser(
        @Query("user_id") user_id : Long
    ) : Call<UserDataResponse>

    @POST("api/update/user")
    fun updateUser(
        @Body jsonparms : UserRegisterRequest
    ) : Call<MessageResponse>

    @GET("api/get/itemloc")
    fun getItemLoc(
        @Query("user_id") user_id : Long
    ) : Call<ItemLocDataResponse>

    @POST("api/update/itemloc")
    fun updateItemLoc(
        @Body jsonparams : ItemLocRegisterRequest
    ) : Call<MessageResponse>

    @GET("api/get/item")
    fun getItem(
        @Query("user_id") user_id : Long, @Query("item_id") item_id : Int
    ) : Call<ItemDataResponse>

    @GET("api/getAll/item")
    fun getAllItem(
        @Query("user_id") user_id : Long
    ) : Call<List<ItemDataResponse>>

    @GET("api/update/item")
    fun updateItem(
        @Query("user_id") user_id : Long, @Query("item_id") item_id : Int, @Query("add") add : Int
    ) : Call<MessageResponse>

    @POST("api/regist/work")
    fun registWork(
        @Body jsonparms : WorkRegisterRequest
    ) : Call<MessageResponse>

    @GET("api/get/work")
    fun getWork(
        @Query("user_id") user_id : Long, @Query("work_date1") work_date1 : String, @Query("work_date2") work_date2 : String
    ) : Call<List<WorkDataResponse>>

    @GET("api/get/weekwork")
    fun getWeekWork(
        @Query("user_id") user_id : Long
    ) : Call<WeekStepNum>

    @GET("api/get/monthwork")
    fun getMonthWork(
        @Query("user_id") user_id : Long
    ) : Call<MonthStepNum>

    @GET("api/get/yearwork")
    fun getYearWork(
        @Query("user_id") user_id : Long
    ) : Call<YearStepNum>
}