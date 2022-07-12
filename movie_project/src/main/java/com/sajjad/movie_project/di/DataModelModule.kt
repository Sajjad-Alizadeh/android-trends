package com.sajjad.movie_project.di

import com.sajjad.movie_project.data.request.RegisterUserRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModelModule {
    @Provides
    @Singleton
    fun provideRegisterUserRequest(): RegisterUserRequest = RegisterUserRequest()

}