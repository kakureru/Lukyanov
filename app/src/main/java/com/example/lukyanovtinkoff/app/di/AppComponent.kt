package com.example.lukyanovtinkoff.app.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {
}