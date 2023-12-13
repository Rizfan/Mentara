package com.rizfan.mentara.data.response

import com.google.gson.annotations.SerializedName

data class GetQuestionResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("listQuestion")
	val listQuestion: List<ListQuestionItem>
)

data class ListQuestionItem(

	@field:SerializedName("questionId")
	val questionId: String,

	@field:SerializedName("question")
	val question: String
)
