package com.rizfan.mentara.ui.screen.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<UserModel>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UserModel>>
        get() = _uiState

    fun getUser() {
        viewModelScope.launch{
            repository.getSession()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}