package com.sajjad.koin

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sajjad.koin.data.repository.Repository
import com.sajjad.koin.data.UserModel
import com.sajjad.koin.retrofit.di.myRetrofitModule
import com.sajjad.koin.room.RoomViewModel
import com.sajjad.koin.room.UserAdapter
import com.sajjad.koin.room.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val myModule = module {
            single { provideDatabase(androidContext()) }
            factory<Repository> { Repository(provideDao(get())) }
            factory { UserModel() }
            factory { UserAdapter() }

            //Room viewModel
            viewModel { RoomViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myRetrofitModule)
        }
    }

    private fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, "userDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    private fun provideDao(userDatabase: UserDatabase) = userDatabase.getDao()
}