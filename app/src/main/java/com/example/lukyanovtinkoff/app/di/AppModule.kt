package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModelFactory
import com.example.lukyanovtinkoff.app.presentation.viewmodel.FavouritesViewModelFactory
import com.example.lukyanovtinkoff.app.presentation.viewmodel.PopularViewModelFactory
import com.example.lukyanovtinkoff.domain.usecase.GetFilmUseCase
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

    @Provides
    fun provideAboutViewModelFactory(
        getFilmUseCase: GetFilmUseCase
    ): AboutViewModelFactory = AboutViewModelFactory(
        getFilmUseCase = getFilmUseCase
    )

    @Provides
    fun provideFavouritesViewModelFactory(

    ): FavouritesViewModelFactory = FavouritesViewModelFactory(

    )
}