package com.example.maktab_q4.data.local

import com.example.maktab_q4.data.local.db.UserDao
import com.example.maktab_q4.model.localmodel.UserDB
import com.example.maktab_q4.model.networkmodel.UserRespons
import ir.mohsenafshar.apps.mkbarchitecture.data.DataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao)  {

    suspend fun addUserToDataBase(userDB: UserDB) {
        userDao.addUserToDataBase(userDB)
    }
}