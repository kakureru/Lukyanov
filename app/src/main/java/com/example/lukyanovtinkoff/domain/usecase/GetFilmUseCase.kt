package com.example.lukyanovtinkoff.domain.usecase

import com.example.lukyanovtinkoff.domain.repository.FilmRepository

class GetFilmUseCase(private val filmRepository: FilmRepository) {
    operator fun invoke(filmId: Int) = filmRepository.getFilm(filmId)
}