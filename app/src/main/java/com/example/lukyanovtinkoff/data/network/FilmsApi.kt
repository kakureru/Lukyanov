package com.example.lukyanovtinkoff.data.network

import com.example.lukyanovtinkoff.data.model.network.FilmResponse
import com.example.lukyanovtinkoff.data.model.network.PopularFilmResponse
import com.example.lukyanovtinkoff.data.model.network.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApi {

    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getPopular() : PopularResponse

    @GET("/api/v2.2/films/{id}")
    suspend fun getFilm(@Path("id") filmId: Int) : FilmResponse
}