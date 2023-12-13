package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class ListResultResponse(

	@field:SerializedName("listResult")
	val listResult: List<ListResultItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ListResultItem(

	@field:SerializedName("idResult")
	val idResult: String,

	@field:SerializedName("dateResult")
	val dateResult: String,

	@field:SerializedName("questionnaireResult")
	val questionnaireResult: String
)
