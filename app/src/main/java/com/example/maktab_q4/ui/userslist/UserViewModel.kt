package com.example.maktab_q4.ui.userslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.maktab_q4.data.remote.network.RetrofitApiProvider
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _userList = MutableLiveData<List<UserResponse>>()
    val userList : LiveData<List<UserResponse>> = _userList

    fun getUsersList() {
        RetrofitApiProvider.userApi.getUserList().enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                Log.d("TAG", response.body().toString())
                _userList.value = response.body()
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.d("TAG", "onFailure: Fail")
            }

        })
    }


}
