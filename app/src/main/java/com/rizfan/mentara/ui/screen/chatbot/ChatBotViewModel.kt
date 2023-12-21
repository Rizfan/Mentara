package com.rizfan.mentara.ui.screen.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizfan.mentara.data.model.ChatUiModel
import com.rizfan.mentara.data.repository.MentaraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel(){
    val conversation: StateFlow<List<ChatUiModel.Message>>
        get() = _conversation
    private val _conversation = MutableStateFlow(
        listOf(ChatUiModel.Message.initConv)
    )

    fun chatbot(message: String) {
        val myChat = ChatUiModel.Message(message, ChatUiModel.Author.me)
        viewModelScope.launch {
            repository.chatbot(message)
                .collect {
                    _conversation.emit(_conversation.value + myChat)
                    delay(1000)
                    _conversation.emit(_conversation.value +
                        ChatUiModel.Message(it.botResponse, ChatUiModel.Author.bot)
                    )
                }
        }
    }
}