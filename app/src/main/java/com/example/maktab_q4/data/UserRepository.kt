package ir.mohsenafshar.apps.mkbarchitecture.data


import com.example.maktab_q4.data.local.LocalDataSource
import com.example.maktab_q4.data.remote.network.RemoteDataSource
import com.example.maktab_q4.di.IoDispatcher
import com.example.maktab_q4.model.localmodel.UserDB
import com.example.maktab_q4.utility.safeApiCall
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class   UserRepository @Inject constructor(
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    companion object {
        const val TAG = "Repository"
    }





    suspend fun  getUserListFlow() = safeApiCall(dispatcher){
        remoteDataSource.getUserListFlow()
    }

    suspend fun getUser(id:String): Response<com.example.maktab_q4.model.networkmodel.UserRespons>{
        return remoteDataSource.getUser(id)
    }

    suspend fun createUser(userReqBody: UserReqBody):Response<String>{
        return remoteDataSource.createUser(userReqBody)
    }

    suspend fun addUserToDataBase(userDB: UserDB){
        localDataSource.addUserToDataBase(userDB)
    }


    fun getDBUserList() = localDataSource.getDBUserList()

    suspend fun getUserDetails(userId:String) = localDataSource.getUserDetails(userId)

    suspend fun uploadImage(id:String,image: MultipartBody.Part):Response<String> = remoteDataSource.uploadImage(id,image)


}