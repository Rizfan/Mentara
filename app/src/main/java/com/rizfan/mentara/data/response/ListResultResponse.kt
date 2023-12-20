package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class ListResultResponse(

	@field:SerializedName("listResult")
	val listResult: List<ListResultItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ListResultItem(

	@field:SerializedName("resultId")
	val resultId: String? = null,

	@field:SerializedName("resultDate")
	val resultDate: String? = null,

	@field:SerializedName("resultQuestionnaire")
	val resultQuestionnaire: String? = null
)
