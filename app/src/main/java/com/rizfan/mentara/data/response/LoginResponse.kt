package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class LoginResult(

	@field:SerializedName("balance")
	val balance: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("noTelp")
	val noTelp: String,

	@field:SerializedName("token")
	val token: String
)
