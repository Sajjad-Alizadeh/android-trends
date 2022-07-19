package com.sajjad.koin.retrofit.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sajjad.koin.retrofit.MoviesAdapter
import com.sajjad.koin.retrofit.RetrofitViewModel
import com.sajjad.koin.retrofit.okhttp.ApiService
import com.sajjad.koin.retrofit.repo.Repository
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val myRetrofitModule = module {
    //time
    single { timeOutTime() }
    single { timeOutUnit() }

    //client
    single { provideClient(get(), get()) }

    //gson converter
    single { provideGson() }

    //baseUrl
    single { provideBaseUrl() }

    //retrofit
    single { provideRetrofit(get(), get(), get()) }

    //apiService
    single { provideApiService(get()) }

    //repository
    factory { Repository(get()) }

    //movie adapter
    factory { MoviesAdapter() }

    //viewModel
    viewModel { RetrofitViewModel(get()) }
}

private fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

private fun provideRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    gson: Gson
) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

private fun provideClient(time: Long, timeUnit: TimeUnit) = OkHttpClient.Builder()
    .callTimeout(time, timeUnit)
    .readTimeout(time, timeUnit)
    .connectTimeout(time, timeUnit)
    .writeTimeout(time, timeUnit)
    .retryOnConnectionFailure(true)
    .build()

private fun provideGson() = GsonBuilder().setLenient().create()
private fun provideBaseUrl() = "https://moviesapi.ir/api/v1/"
private fun timeOutTime(): Long = 30
private fun timeOutUnit(): TimeUnit = TimeUnit.SECONDS