package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import com.example.lukyanovtinkoff.domain.usecase.GetFilmUseCase
import com.example.lukyanovtinkoff.domain.usecase.GetPopularFilmsUseCase
import com.example.lukyanovtinkoff.domain.usecase.SaveFilmUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetPopularFilmsUseCase(filmRepository: FilmRepository): GetPopularFilmsUseCase =
        GetPopularFilmsUseCase(filmRepository = filmRepository)

    @Provides
    fun provideGetFilmUseCase(filmRepository: FilmRepository): GetFilmUseCase =
        GetFilmUseCase(filmRepository = filmRepository)

    @Provides
    fun provideSaveFilmUseCase(filmRepository: FilmRepository): SaveFilmUseCase =
        SaveFilmUseCase(filmRepository = filmRepository)
}