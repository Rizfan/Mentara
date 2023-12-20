package com.rizfan.mentara.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.data.response.RegisterResponse
import com.rizfan.mentara.ui.common.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<AuthState<RegisterResponse>> =
        MutableStateFlow(AuthState.Unauthorized)
    val uiState: StateFlow<AuthState<RegisterResponse>>
        get() = _uiState

    fun register(email: String,notTelp : String, name : String, password: String) {
        _uiState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                repository.register(email,notTelp,name,password)
                    .catch{
                        _uiState.value = AuthState.Error(it.message.toString())
                    }
                    .collect {
                        _uiState.value = AuthState.Success(it)
                    }
            }catch (e:HttpException){
                _uiState.value = AuthState.Error(e.message.toString())
            }
        }
    }
}