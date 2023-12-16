package com.rizfan.mentara.ui.screen.register

import androidx.lifecycle.ViewModel
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.data.response.RegisterResponse
import com.rizfan.mentara.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<RegisterResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<RegisterResponse>>
        get() = _uiState

    suspend fun register(email: String,notTelp : String, name : String, password: String) {
        try {
            _uiState.value = UiState.Success(repository.register(email,notTelp, name, password))
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message.toString())
        }
    }
}