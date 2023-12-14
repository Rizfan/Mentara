package com.rizfan.mentara.di

import com.rizfan.mentara.data.pref.UserPreference
import com.rizfan.mentara.data.retrofit.ApiConfig
import com.rizfan.mentara.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(dataStore: UserPreference): ApiService {
        val user = runBlocking {
            dataStore.getSession().first()
        }
        return ApiConfig.getApiService(user.token)
    }
}