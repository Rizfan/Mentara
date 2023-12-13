package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class ChatBotResponse(

	@field:SerializedName("botResponse")
	val botResponse: String,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
