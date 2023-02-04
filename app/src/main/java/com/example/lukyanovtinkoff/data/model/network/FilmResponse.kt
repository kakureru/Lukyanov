package com.example.lukyanovtinkoff.data.model.network

import com.example.lukyanovtinkoff.domain.model.FilmExt
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("nameRu") val name: String,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("year") val year: Int,
    @SerializedName("countries") val countries: List<Country>,
    @SerializedName("genres") val genres: List<Genre>
)

fun FilmResponse.toDomain() = FilmExt(
    name = name,
    posterUrl = posterUrl,
    description = description,
    year = year,
    countries = countries.map { it.toDomain() },
    genres = genres.map { it.toDomain() }
)