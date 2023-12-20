package com.rizfan.mentara.ui.screen.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.data.response.DetailResultResponse
import com.rizfan.mentara.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailResultResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<DetailResultResponse>> = _uiState

    fun getDetailResult(id: Int) {
        viewModelScope.launch {
            repository.getDetailResult(id)
                .onStart {
                    _uiState.value = UiState.Loading
                }
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}