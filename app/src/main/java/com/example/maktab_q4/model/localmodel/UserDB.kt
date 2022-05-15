package com.example.maktab_q4.model.localmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDB (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,
    val _id: String ,
    val firstName: String,
    //val hobbies: List<String>,
    val image: String?,
    val lastName: String,
    val nationalCode: String
)