package com.rizfan.mentara.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.data.response.LoginResponse
import com.rizfan.mentara.ui.common.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MentaraRepository
): ViewModel(){
    private val _uiState : MutableStateFlow<AuthState<LoginResponse>> = MutableStateFlow(AuthState.Unauthorized)
    val uiState : MutableStateFlow<AuthState<LoginResponse>>
        get() = _uiState
    fun login(email: String, password: String) {
        _uiState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                repository.login(email,password)
                    .catch {
                        _uiState.value = AuthState.Error(it.message.toString())
                    }
                    .collect{
                        if(!it.error){
                            _uiState.value = AuthState.Success(it)
                            saveSession(
                                UserModel(
                                    userId = it.loginResult.userId,
                                    name = it.loginResult.name,
                                    email = it.loginResult.email,
                                    noTelp = it.loginResult.noTelp,
                                    token = it.loginResult.token,
                                    balance = it.loginResult.balance,
                                    isLogin = true
                                )

                            )
                        }else{
                            _uiState.value = AuthState.Error("Login Failed")
                        }

                    }
            }catch (e : HttpException){
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
                _uiState.value = AuthState.Error(errorResponse.message)
            }
        }
    }

    fun saveSession(user : UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}