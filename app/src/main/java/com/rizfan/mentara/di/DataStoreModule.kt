package com.rizfan.mentara.di

import com.rizfan.mentara.data.pref.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun userPreference(): UserPreference {
        return UserPreference()
    }
}