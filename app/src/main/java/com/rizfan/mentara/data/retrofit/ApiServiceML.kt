package com.rizfan.mentara.data.retrofit

import com.rizfan.mentara.data.response.ChatBotResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServiceML {

    @FormUrlEncoded
    @POST("chatbot")
    suspend fun chatbot(
        @Field("Question") question: String
    ): ChatBotResponse

}