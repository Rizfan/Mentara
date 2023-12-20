package com.rizfan.mentara.data.retrofit

import com.rizfan.mentara.data.response.BalanceResponse
import com.rizfan.mentara.data.response.ChatBotResponse
import com.rizfan.mentara.data.response.DetailResultResponse
import com.rizfan.mentara.data.response.GetQuestionResponse
import com.rizfan.mentara.data.response.ListResultResponse
import com.rizfan.mentara.data.response.LoginResponse
import com.rizfan.mentara.data.response.QuestionnaireResultResponse
import com.rizfan.mentara.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("noTelp") noTelp: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("balance")
    suspend fun balance(
        @Field("balance") balance: Int
    ): BalanceResponse

    @GET("questions")
    suspend fun getQuestions(): GetQuestionResponse

    @FormUrlEncoded
    @POST("/questions/result")
    suspend fun getResult(
        @Field("point") point: Int
    ): QuestionnaireResultResponse

    @GET("questions/result")
    suspend fun getResults(): ListResultResponse

    @GET("questions/result/{resultId}")
    suspend fun getDetailResult(
        @Path("resultId") resultId: Int
    ): DetailResultResponse

    @FormUrlEncoded
    @POST("chatbot")
    suspend fun chatbot(
        @Field("message") message: String
    ): ChatBotResponse
}