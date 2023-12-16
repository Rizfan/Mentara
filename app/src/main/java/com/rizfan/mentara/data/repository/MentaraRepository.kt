package com.rizfan.mentara.data.repository

import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.data.pref.UserPreference
import com.rizfan.mentara.data.response.ListResultItem
import com.rizfan.mentara.data.response.LoginResponse
import com.rizfan.mentara.data.response.RegisterResponse
import com.rizfan.mentara.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MentaraRepository @Inject constructor(
    private val userPreference : UserPreference,
    private val apiService: ApiService
){

    suspend fun register(name: String,noTelp : String, email: String, password: String): RegisterResponse {
        return apiService.register(name, noTelp, email, password)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
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

    suspend fun balance(balance: Int) {
        apiService.balance(balance)
    }

    suspend fun chatbot(message: String) {
        apiService.chatbot(message)
    }

    suspend fun getQuestion() {
        apiService.getQuestions()
    }

    suspend fun getQuestionnaireResult(point: Int) {
        apiService.getResult(point)
    }

    suspend fun getListResult(): Flow<List<ListResultItem>> {
        return flowOf(apiService.getResults().listResult)
    }
}