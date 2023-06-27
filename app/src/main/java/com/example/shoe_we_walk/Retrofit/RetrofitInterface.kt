package com.example.shoe_we_walk.Retrofit

import com.example.shoe_we_walk.Data.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("/api/check/userExist")
    fun isUserExist(
        @Query("user_id") user_id: Long
    ): Call<MessageResponse>

    @POST("api/regist/user")
    fun registUser(
        @Body userRegisterRequest: UserRegisterRequest
    ): Call<MessageResponse>

    @PUT("api/updateUser")
    fun updateUser(
        @Body userRegisterRequest: UserRegisterRequest
    ): Call<MessageResponse>

    @GET("api/user/user_id")
    fun getUser(
        @Query("user_id") user_id: Long
    ): Call<UserDataResponse>

    @GET("api/getItemLoc")
    fun getItemLoc(
        @Query("item_id") item_id: Long
    ): Call<ItemLocationResponse>

    @PUT("api/updateItemLoc")
    fun updateItemLoc(
        @Body itemLocRegisterRequest: ItemLocRegisterRequest
    ): Call<MessageResponse>

    @GET("api/getAllItem")
    fun getAllItem(
        @Query("user_id") user_id: Long
    ): Call<List<ItemDataResponse>>

    @GET("api/update/item")
    fun updateItem(
        @Query("user_id") user_id: Long,
        @Query("item_id") item_id: Int,
        @Query("add") add: Int
    ): Call<MessageResponse>





    @POST("api/insert/user")
    fun insertUser(
        @Body jsonparms : UserRegisterRequest
    ) : Call<UserIdResponse>

    @POST("api/insert/item")
    fun insertItem(
        @Query("user_id") user_id : Int, @Body jsonparms : ItemRegisterRequest
    ) : Call<MessageResponse>

    @POST("api/insert/work")
    fun insertWork(
        @Query("user_id") user_id : Int, @Body jsonparms : WorkRegisterRequest
    ) : Call<MessageResponse>

    @GET("api/select/user")
    fun selectUser(
        @Query("user_id") user_id : Int
    ) : Call<UserDataResponse>

    @GET("api/select/item")
    fun selectItem(
        @Query("user_id") user_id : Int, @Query("item_id") item_id : Int
    ) : Call<ItemDataResponse>

    @GET("api/selectAll/item")
    fun selectAllItem(
        @Query("user_id") user_id : Int
    ) : Call<List<ItemDataResponse>>

    @GET("api/select/work")
    fun selectWork(
        @Query("user_id") user_id : Int, @Query("work_date1") work_date1 : String, @Query("work_date2") work_date2 : String
    ) : Call<List<WorkDataResponse>>

    @POST("api/update/user")
    fun updateUser(
        @Query("user_id") user_id : Int, @Body jsonparms : UserRegisterRequest
    ) : Call<MessageResponse>

    /*@POST("api/update/item")
    fun updateItem(
        @Query("user_id") user_id : Int, @Body jsonparms : ItemRegisterRequest
    ) : Call<MessageResponse>*/
}