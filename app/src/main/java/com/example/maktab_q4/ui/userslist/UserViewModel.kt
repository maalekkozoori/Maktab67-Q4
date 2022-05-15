package com.example.maktab_q4.ui.userslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktab_q4.model.localmodel.UserDB
import com.example.maktab_q4.model.networkmodel.UserRespons
import com.example.maktab_q4.utility.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mohsenafshar.apps.mkbarchitecture.data.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _userListFlow : MutableStateFlow<ResultWrapper<List<UserRespons>>> = MutableStateFlow(ResultWrapper.Loading)
    val userListFlow  = _userListFlow.asStateFlow()

    fun getUserList(){
        viewModelScope.launch {
            val myResultWrapper = repository.getUserListFlow()  // Naming this variable only for Mahdi's love(مهدی پورکاظمی)
            myResultWrapper.collect {
                _userListFlow.emit(it)
            }
        }
    }

    private val _user = MutableLiveData<UserRespons>()
    val user : LiveData<UserRespons> = _user
    fun getUser(id:String):LiveData<UserRespons>{
        viewModelScope.launch {
            val response = repository.getUser(id)
            //_user.value = UserDetails("21321","maalek", listOf(),"khsdf","Ghoba","343")
            _user.postValue(response.body())
            if(response.isSuccessful){
                Log.d("USER", "getUser: "+response.message())
            }

        }

        return _user
    }


    private val _createdUserId = MutableLiveData<String>()
    val createdUserId : LiveData<String> = _createdUserId

    fun createUser(userReqBody: UserReqBody):LiveData<String>{
        viewModelScope.launch {
            _createdUserId.postValue(repository.createUser(userReqBody).body())
        }
        return _createdUserId
    }


    fun addUserToDataBase(userDB: UserDB){
        viewModelScope.launch {
            repository.addUserToDataBase(userDB)
        }
    }




    init {
        getUserList()
    }

}
