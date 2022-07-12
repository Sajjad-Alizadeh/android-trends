package com.sajjad.hilt.simple.di.module

import android.content.Context
import com.sajjad.hilt.R
import com.sajjad.hilt.simple.di.APP_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StringModule {
    @Provides
    @Singleton
    @Named(APP_NAME)
    fun provideAppName(@ApplicationContext context: Context): String {
        return context.getString(R.string.app_name)
    }
}