package com.rizfan.mentara.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rizfan.mentara.data.model.ChatBotModel

@Dao
interface ChatBotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatBot(chatBotModel: ChatBotModel)

    @Query("SELECT * FROM chatbot where message = :message")
    suspend fun getChatBot(message: String): ChatBotModel

}