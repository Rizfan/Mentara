package com.rizfan.mentara.di

import android.content.Context
import androidx.room.Room
import com.rizfan.mentara.data.database.ChatBotDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideChatBotDao(database: ChatBotDatabase) = database.chatBotDao()

    @Singleton
    @Provides
    fun provideChatBotDatabase(@ApplicationContext context: Context) : ChatBotDatabase {
        return Room.databaseBuilder(
            context,
            ChatBotDatabase::class.java,
            "ChatBotDatabase"
        ).build()
    }

}