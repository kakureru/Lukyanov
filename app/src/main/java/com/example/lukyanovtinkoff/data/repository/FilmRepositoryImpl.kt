package com.example.lukyanovtinkoff.data.repository

import com.example.lukyanovtinkoff.data.db.FilmsDao
import com.example.lukyanovtinkoff.data.model.db.FilmEntity
import com.example.lukyanovtinkoff.data.model.db.toDomain
import com.example.lukyanovtinkoff.data.model.network.toDomain
import com.example.lukyanovtinkoff.data.network.FilmsApi
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.repository.BaseRepository
import com.example.lukyanovtinkoff.domain.repository.FilmRepository
import com.example.lukyanovtinkoff.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepositoryImpl(
    private val filmsApi: FilmsApi,
    private val filmsDao: FilmsDao
) : BaseRepository(), FilmRepository {

    override val popularFilms: Flow<Either<String, List<Film>>> = doRequest {
        filmsApi.getPopular().toDomain()
    }

    override val favouriteFilms = filmsDao.getAll().map { films ->
        films.map { it.toDomain() }
    }

    override fun getFilm(filmId: Int): Flow<Either<String, Film>> = doRequest {
        filmsApi.getFilm(filmId).toDomain()
    }

    override suspend fun saveFilm(film: Film) {
        filmsDao.saveFilm(toData(film))
    }

    private fun toData(film: Film) = FilmEntity(
        id = film.id,
        name = film.name,
        posterUrl = film.posterUrl,
        posterUrlPreview = film.posterUrlPreview,
        description = film.description,
        year = film.year,
        countries = film.countries,
        genres = film.genres,
        favourite = true
    )

}