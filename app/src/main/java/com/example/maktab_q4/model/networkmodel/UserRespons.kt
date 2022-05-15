package com.example.maktab_q4.model.networkmodel

data class UserRespons(
    val _id: String ,
    val firstName: String,
    val hobbies: List<String>,
    val image: String?,
    val lastName: String,
    val nationalCode: String
)