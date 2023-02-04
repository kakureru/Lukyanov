package com.example.lukyanovtinkoff.app.di

import android.app.Application
import androidx.room.Room
import com.example.lukyanovtinkoff.data.db.AppDatabase
import com.example.lukyanovtinkoff.data.db.FilmsDao
import com.example.lukyanovtinkoff.data.network.FilmsApi
import com.example.lukyanovtinkoff.data.repository.FilmRepositoryImpl
import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(val application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFilmsDao(database: AppDatabase): FilmsDao = database.FilmsDao()

    @Singleton
    @Provides
    fun provideFilmRepository(
        filmsApi: FilmsApi,
        filmsDao: FilmsDao
    ): FilmRepository =
        FilmRepositoryImpl(
            filmsApi = filmsApi,
            filmsDao = filmsDao
        )
}