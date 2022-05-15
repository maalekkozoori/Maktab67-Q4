package com.example.maktab_q4.data.remote.network



import com.example.maktab_q4.model.networkmodel.UserRespons
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: UserApi) {

    suspend fun getUserListFlow(): Response<List<UserRespons>> {
        return apiService.getUserListFlow()
    }

    suspend fun getUser(id:String): Response<UserRespons>{
        return apiService.getUser(id)
    }

    suspend fun createUser(userReqBody: UserReqBody):Response<String>{
        return apiService.createUser(userReqBody)
    }


}