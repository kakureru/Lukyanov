package com.example.lukyanovtinkoff.data.network

import com.example.lukyanovtinkoff.data.model.network.FilmResponse
import com.example.lukyanovtinkoff.data.model.network.PopularResponse
import retrofit2.http.GET

interface FilmsApi {

    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getPopular() : PopularResponse

    @GET("/api/v2.2/films/top/") // add film id at the end
    suspend fun getDescription() : FilmResponse
}