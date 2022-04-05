package ir.mohsenafshar.apps.mkbarchitecture.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.maktab_q4.model.localmodel.User
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import java.util.concurrent.ExecutorService

class   UserRepository(
    private val executorService: ExecutorService,
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource
) {

    companion object {
        const val TAG = "Repository"
    }



    fun saveUserList(userList: List<User>) {
        executorService.submit {
            localDataSource.saveUserList(userList)
        }

    }

    fun getUserList(): LiveData<List<User>> {
        val liveData = MutableLiveData<List<User>>()

        executorService.submit {
            val remoteData: List<User> = remoteDataSource.getUserList()
            localDataSource.saveUserList(remoteData)
            liveData.postValue(remoteData)
        }

        return liveData
    }



}