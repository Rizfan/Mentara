package com.rizfan.mentara.data.repository

import com.rizfan.mentara.data.database.ChatBotDao
import com.rizfan.mentara.data.model.ChatBotModel
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.data.pref.UserPreference
import com.rizfan.mentara.data.response.BalanceResponse
import com.rizfan.mentara.data.response.ChatBotResponse
import com.rizfan.mentara.data.response.DetailResultResponse
import com.rizfan.mentara.data.response.ListQuestionItem
import com.rizfan.mentara.data.response.ListResultResponse
import com.rizfan.mentara.data.response.LoginResponse
import com.rizfan.mentara.data.response.QuestionnaireResultResponse
import com.rizfan.mentara.data.response.RegisterResponse
import com.rizfan.mentara.data.retrofit.ApiService
import com.rizfan.mentara.data.retrofit.ApiServiceML
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MentaraRepository @Inject constructor(
    private val userPreference : UserPreference,
    private val apiService: ApiService,
    private val chatBotDao: ChatBotDao,
    private val apiServiceML: ApiServiceML
){

    suspend fun register(name: String,noTelp : String, email: String, password: String): Flow<RegisterResponse> {
        return flowOf(apiService.register(name, noTelp, email, password))
    }

    suspend fun login(email: String, password: String): Flow<LoginResponse> {
        return flowOf(apiService.login(email, password))
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }
    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }
    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun balance(balance: Int) : Flow<BalanceResponse> {
        return flowOf(apiService.balance(balance))
    }

    suspend fun chatbot(message: String) : Flow<ChatBotResponse> {
        val response = apiServiceML.chatbot(message)
        chatBotDao.insertChatBot(
            ChatBotModel(
                0,
                message,
                response.botResponse
            )
        )
        return flowOf(response)
    }

    suspend fun getQuestion(balance: Int) : Flow<List<ListQuestionItem>> {
        balance(balance)
        return flowOf(apiService.getQuestions().listQuestion)
    }

    suspend fun getQuestionnaireResult(point: Int) : Flow<QuestionnaireResultResponse> {
        return flowOf(apiService.getResult(point))
    }

    suspend fun getDetailResult(resultId: Int) : Flow<DetailResultResponse> {
        return flowOf(apiService.getDetailResult(resultId))
    }

    suspend fun getListResult(): Flow<ListResultResponse> {
        return flowOf(apiService.getResults())
    }
}