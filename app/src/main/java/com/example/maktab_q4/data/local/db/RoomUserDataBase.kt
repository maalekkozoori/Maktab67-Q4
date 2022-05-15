package com.example.maktab_q4.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maktab_q4.model.localmodel.UserDB

@Database(entities = [UserDB::class], version = 1, exportSchema = true)
abstract class RoomUserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao


}