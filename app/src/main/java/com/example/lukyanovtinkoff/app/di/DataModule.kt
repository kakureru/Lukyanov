package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.data.network.FilmsApi
import com.example.lukyanovtinkoff.data.repository.FilmRepositoryImpl
import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideFilmRepository(filmsApi: FilmsApi): FilmRepository =
        FilmRepositoryImpl(filmsApi = filmsApi)
}