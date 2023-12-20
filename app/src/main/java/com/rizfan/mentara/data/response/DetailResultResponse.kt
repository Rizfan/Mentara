package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class DetailResultResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Result(

	@field:SerializedName("resultQuestionnaire")
	val resultQuestionnaire: String? = null,

	@field:SerializedName("resultId")
	val resultId: Int? = null,

	@field:SerializedName("resultDate")
	val resultDate: String? = null
)
