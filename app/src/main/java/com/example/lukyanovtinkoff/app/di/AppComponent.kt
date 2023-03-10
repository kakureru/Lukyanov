package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.app.presentation.view.AboutFragment
import com.example.lukyanovtinkoff.app.presentation.view.FavouritesFragment
import com.example.lukyanovtinkoff.app.presentation.view.PopularFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(fragment: PopularFragment)
    fun inject(fragment: AboutFragment)
    fun inject(fragment: FavouritesFragment)
}