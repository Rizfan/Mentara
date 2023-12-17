package com.rizfan.mentara.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.data.response.LoginResponse
import com.rizfan.mentara.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MentaraRepository
): ViewModel(){
    private val _uiState : MutableStateFlow<UiState<LoginResponse>> = MutableStateFlow(UiState.Loading)
    val uiState : MutableStateFlow<UiState<LoginResponse>>
        get() = _uiState
    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email,password)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    suspend fun saveSession(user : UserModel) = repository.saveSession(user)
}