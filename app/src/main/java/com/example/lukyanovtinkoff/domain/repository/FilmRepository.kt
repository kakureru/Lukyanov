package com.example.lukyanovtinkoff.domain.repository

import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    val popularFilms: Flow<Either<String, List<Film>>>

    fun getFilm(filmId: Int): Flow<Either<String, Film>>

    suspend fun saveFilm(film: Film)
}