package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import com.example.lukyanovtinkoff.domain.usecase.GetPopularFilmsUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetPopularFilmsUseCase(filmRepository: FilmRepository): GetPopularFilmsUseCase =
        GetPopularFilmsUseCase(filmRepository = filmRepository)
}