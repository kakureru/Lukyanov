package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.app.presentation.viewmodel.PopularViewModelFactory
import com.example.lukyanovtinkoff.domain.usecase.GetPopularFilmsUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providePopularViewModelFactory(
        getPopularFilmsUseCase: GetPopularFilmsUseCase
    ): PopularViewModelFactory = PopularViewModelFactory(
        getPopularFilmsUseCase = getPopularFilmsUseCase
    )
}