package com.sajjad.movie_project.di

import android.content.Context
import androidx.room.Room
import com.sajjad.movie_project.common.Constants
import com.sajjad.movie_project.data.db.AppDataBase
import com.sajjad.movie_project.data.db.Dao
import com.sajjad.movie_project.data.db.MovieEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, Constants.DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(appDataBase: AppDataBase): Dao {
        return appDataBase.getDao()
    }

    @Provides
    @Singleton
    fun provideEntity(): MovieEntity {
        return MovieEntity()
    }
}