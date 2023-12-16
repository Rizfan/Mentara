package com.rizfan.mentara.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizfan.mentara.data.repository.MentaraRepository
import com.rizfan.mentara.data.response.ListResultItem
import com.rizfan.mentara.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: MentaraRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ListResultItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ListResultItem>>>
        get() = _uiState

    fun getHistory() {
        viewModelScope.launch {
            repository.getListResult()
                .catch{
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}