package com.example.lukyanovtinkoff.domain.usecase

import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.repository.FilmRepository

class DeleteFilmUseCase(private val filmRepository: FilmRepository) {
    suspend operator fun invoke(film: Film) = filmRepository.deleteFilm(film)
}