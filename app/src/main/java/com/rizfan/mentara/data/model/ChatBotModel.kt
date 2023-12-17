package com.rizfan.mentara.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatbot")
data class ChatBotModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val Id:Int,

    @ColumnInfo(name = "message")
    val message:String,

    @ColumnInfo(name = "botResponse")
    val botResponse : String,
)