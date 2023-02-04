package com.example.lukyanovtinkoff.data.model.network

import com.google.gson.annotations.SerializedName

data class FilmResponse (
    @SerializedName("filmId") val id: Int,
    @SerializedName("nameRu") val name: String,
    @SerializedName("year") val year: Int,
    @SerializedName("countries") val countries: List<String>,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
)