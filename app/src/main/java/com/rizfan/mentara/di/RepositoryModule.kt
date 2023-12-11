package com.rizfan.mentara.di

import com.rizfan.mentara.data.repository.MentaraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMentaraRepository(): MentaraRepository {
        return MentaraRepository()
    }
}