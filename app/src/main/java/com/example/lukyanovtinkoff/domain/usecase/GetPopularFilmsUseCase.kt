package com.example.lukyanovtinkoff.domain.usecase

import com.example.lukyanovtinkoff.domain.repository.FilmRepository

class GetPopularFilmsUseCase(private val filmRepository: FilmRepository) {
    operator fun invoke() = filmRepository.popularFilms
}