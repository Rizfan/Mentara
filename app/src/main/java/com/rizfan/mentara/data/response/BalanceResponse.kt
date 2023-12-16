package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class BalanceResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
