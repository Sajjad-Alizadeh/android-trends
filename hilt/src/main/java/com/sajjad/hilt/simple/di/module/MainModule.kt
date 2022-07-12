package com.sajjad.hilt.simple.di.module

import com.sajjad.hilt.simple.di.USERNAME
import com.sajjad.hilt.simple.di.qualifier.Family
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideName(): String {
        return "Sajjad"
    }

    @Provides
    @Singleton
    @Family
    fun provideFamily(): String {
        return "Alizadeh"
    }

    @Provides
    @Singleton
    @Named(USERNAME)
    fun provideUsername(): String {
        return "sajjadalizz"
    }
}