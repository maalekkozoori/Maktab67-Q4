package ir.mohsenafshar.apps.mkbarchitecture.data

import com.example.maktab_q4.model.localmodel.UserDB
import com.example.maktab_q4.model.networkmodel.UserRespons
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import retrofit2.Response
import javax.inject.Singleton


@Singleton
interface DataSource {
    fun getUser(id:String): Response<UserRespons>
    suspend fun getUserListFlow(): Response<List<UserRespons>>
    fun saveUserList(userDBS: List<UserDB>)
    suspend fun createUser(userReqBody: UserReqBody): Response<String>

    suspend fun addUserToDataBase(userDB: UserDB)

}