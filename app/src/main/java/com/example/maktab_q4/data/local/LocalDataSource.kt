package com.example.maktab_q4.data.local

import com.example.maktab_q4.data.local.db.UserDao
import com.example.maktab_q4.model.localmodel.UserDB
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao)  {

    suspend fun addUserToDataBase(userDB: UserDB) {
        userDao.addUserToDataBase(userDB)
    }

    fun getDBUserList() = userDao.getDBUserList()

    suspend fun getUserDetails(userId:String)= userDao.getUserDetails(userId)
}