package com.example.lukyanovtinkoff.data.model.network

import com.example.lukyanovtinkoff.domain.model.Film
import com.google.gson.annotations.SerializedName

data class PopularFilmResponse (
    @SerializedName("filmId") val id: Int,
    @SerializedName("nameRu") val name: String,
    @SerializedName("year") val year: Int,
    @SerializedName("countries") val countries: List<Country>,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
)

fun PopularFilmResponse.toDomain() = Film(
    id = id,
    name = name,
    year = year,
    countries = countries.map { it.toDomain() },
    genres = genres.map { it.toDomain() },
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview
)