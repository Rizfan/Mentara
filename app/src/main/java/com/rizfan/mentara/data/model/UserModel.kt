package com.rizfan.mentara.data.model

data class UserModel(
    val userId : Int,
    val name : String,
    val noTelp : String,
    val balance : Int,
    val token : String = "",
    val email : String,
)