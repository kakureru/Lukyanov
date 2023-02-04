package com.example.lukyanovtinkoff.domain.usecase

import com.example.lukyanovtinkoff.domain.repository.FilmRepository

class GetFavouriteFilmsUseCase(private val filmRepository: FilmRepository) {
    operator fun invoke() = filmRepository.favouriteFilms
}