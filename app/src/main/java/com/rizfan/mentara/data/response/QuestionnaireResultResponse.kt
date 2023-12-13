package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class QuestionnaireResultResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("questionnaireResult")
	val questionnaireResult: String
)
