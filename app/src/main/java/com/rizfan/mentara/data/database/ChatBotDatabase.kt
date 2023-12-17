package com.rizfan.mentara.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rizfan.mentara.data.model.ChatBotModel

@Database(entities = [ChatBotModel::class], version = 1)
abstract class ChatBotDatabase : RoomDatabase() {
    abstract fun chatBotDao(): ChatBotDao
}