package com.sajjad.hilt.room.di

import android.content.Context
import androidx.room.Room
import com.sajjad.hilt.room.model.User
import com.sajjad.hilt.room.DATABASE_NAME
import com.sajjad.hilt.room.db.AppDatabase
import com.sajjad.hilt.room.db.MyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideUser(): User {
        return User()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): MyDao {
        return appDatabase.getDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}