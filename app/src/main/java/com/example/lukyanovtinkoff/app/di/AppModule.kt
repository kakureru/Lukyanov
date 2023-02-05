package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModelFactory
import com.example.lukyanovtinkoff.app.presentation.viewmodel.FavouritesViewModelFactory
import com.example.lukyanovtinkoff.app.presentation.viewmodel.PopularViewModelFactory
import com.example.lukyanovtinkoff.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providePopularViewModelFactory(
        getPopularFilmsUseCase: GetPopularFilmsUseCase,
        saveFilmUseCase: SaveFilmUseCase,
        deleteFilmUseCase: DeleteFilmUseCase
    ): PopularViewModelFactory = PopularViewModelFactory(
        getPopularFilmsUseCase = getPopularFilmsUseCase,
        saveFilmUseCase = saveFilmUseCase,
        deleteFilmUseCase = deleteFilmUseCase
    )

    @Provides
    fun provideAboutViewModelFactory(
        getFilmUseCase: GetFilmUseCase
    ): AboutViewModelFactory = AboutViewModelFactory(
        getFilmUseCase = getFilmUseCase
    )

    @Provides
    fun provideFavouritesViewModelFactory(
        getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase,
        saveFilmUseCase: SaveFilmUseCase,
        deleteFilmUseCase: DeleteFilmUseCase
    ): FavouritesViewModelFactory = FavouritesViewModelFactory(
        getFavouriteFilmsUseCase = getFavouriteFilmsUseCase,
        saveFilmUseCase = saveFilmUseCase,
        deleteFilmUseCase = deleteFilmUseCase
    )
}