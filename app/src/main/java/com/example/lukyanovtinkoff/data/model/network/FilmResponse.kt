package com.example.lukyanovtinkoff.data.model.network

import com.example.lukyanovtinkoff.domain.model.Film
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("kinopoiskId") val kinopoiskId: Int,
    @SerializedName("nameRu") val name: String,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
    @SerializedName("description") val description: String?,
    @SerializedName("year") val year: Int,
    @SerializedName("countries") val countries: List<Country>,
    @SerializedName("genres") val genres: List<Genre>
)

fun FilmResponse.toDomain() = Film(
    id = if (filmId > 0) filmId else kinopoiskId,
    name = name,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview,
    description = description ?: "",
    year = year,
    countries = countries.map { it.toDomain() },
    genres = genres.map { it.toDomain() }
)