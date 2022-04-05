package com.example.maktab_q4.data.remote.network

import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface UserApi {

   /* @GET("users")
    fun getUserList(@QueryMap filters: HashMap<String, String> = hashMapOf()): Call<List<UserResponse>>*/

    @GET("users")
    fun getUserList(): Call<List<UserResponse>>

    @POST("users")
    fun createUser(
        @Body userReqBody: UserReqBody
    ): Call<String>

    @Multipart
    @POST("users/{id}/image")
    fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Call<Any>
}