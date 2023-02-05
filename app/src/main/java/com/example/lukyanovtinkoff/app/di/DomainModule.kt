package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import com.example.lukyanovtinkoff.domain.usecase.*
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

    @Provides
    fun provideGetFavouriteFilmsUseCase(filmRepository: FilmRepository): GetFavouriteFilmsUseCase =
        GetFavouriteFilmsUseCase(filmRepository = filmRepository)

    @Provides
    fun provideDeleteFilmUseCase(filmRepository: FilmRepository): DeleteFilmUseCase =
        DeleteFilmUseCase(filmRepository = filmRepository)
}