package com.sajjad.hilt.retrofit.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sajjad.hilt.BuildConfig
import com.sajjad.hilt.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(true) // to retry api once when error occurred
            .build()
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("baseUrl") baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}