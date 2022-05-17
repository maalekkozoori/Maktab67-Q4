package com.example.maktab_q4.data.local.db

import androidx.room.*
import com.example.maktab_q4.model.localmodel.UserDB
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserToDataBase(vararg userDB: UserDB)

    @Update
    suspend fun updateUser(userDB: UserDB)

    @Delete
    suspend fun deleteUser(userDB: UserDB)

    @Query("SELECT * FROM user_table")
    fun getDBUserList():Flow<List<UserDB>>

    @Query("SELECT * FROM user_table WHERE _id == :userId")
    suspend fun getUserDetails(userId:String): UserDB



}