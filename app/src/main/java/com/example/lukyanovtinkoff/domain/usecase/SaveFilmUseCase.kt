package com.example.lukyanovtinkoff.domain.usecase

import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.repository.FilmRepository

class SaveFilmUseCase(private val filmRepository: FilmRepository) {
    suspend operator fun invoke(film: Film) = filmRepository.saveFilm(film)
}