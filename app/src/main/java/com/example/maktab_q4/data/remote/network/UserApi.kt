package com.example.maktab_q4.data.remote.network


import com.example.maktab_q4.model.networkmodel.UserRespons
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*



interface UserApi {


    @GET("users?")
    suspend fun getUserListFlow(): Response<List<UserRespons>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): Response<UserRespons>

    @POST("users")
    suspend fun createUser(@Body userReqBody: UserReqBody): Response<String>

    @Multipart
    @POST("users/{id}/image")
    suspend fun uploadImage(@Path("id") id: String,@Part image: MultipartBody.Part): Response<String>
}