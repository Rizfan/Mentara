package com.rizfan.mentara.data.model

data class UserModel(
    val id : Int,
    val name : String,
    val email : String,
    val phone : String,
    val token : String = "",
)