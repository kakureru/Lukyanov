package com.example.lukyanovtinkoff.data.repository

import com.example.lukyanovtinkoff.data.model.network.toDomain
import com.example.lukyanovtinkoff.data.network.FilmsApi
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.model.FilmExt
import com.example.lukyanovtinkoff.domain.repository.BaseRepository
import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import com.example.lukyanovtinkoff.domain.utils.Either
import kotlinx.coroutines.flow.Flow

class FilmRepositoryImpl(
    private val filmsApi: FilmsApi
) : BaseRepository(), FilmRepository {

    override val popularFilms: Flow<Either<String, List<Film>>> = doRequest {
        filmsApi.getPopular().toDomain()
    }

    override fun getFilm(filmId: Int): Flow<Either<String, FilmExt>> = doRequest {
        filmsApi.getFilm(filmId).toDomain()
    }
}