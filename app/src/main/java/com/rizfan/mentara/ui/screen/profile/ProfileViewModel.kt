package com.rizfan.mentara.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Flow<UserModel>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Flow<UserModel>>>
        get() = _uiState

    fun getUser() {
        _uiState.value = UiState.Success(repository.getSession())
    }
}