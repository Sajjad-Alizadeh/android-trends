package com.sajjad.movie_project.di

import androidx.datastore.preferences.protobuf.Api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sajjad.movie_project.common.Constants.BASE_URL
import com.sajjad.movie_project.common.Constants.SERVER_CONNECTION_TIME
import com.sajjad.movie_project.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideConnectionTime(): Long {
        return SERVER_CONNECTION_TIME
    }

    @Provides
    @Singleton
    fun provideConnectionTimeUnit(): TimeUnit {
        return TimeUnit.SECONDS
    }


    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return BASE_URL
    }


    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideClient(
        timeOut: Long,
        timeUnit: TimeUnit,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit)
            .connectTimeout(timeOut, timeUnit)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(url: String, client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}