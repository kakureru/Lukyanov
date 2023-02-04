package com.example.lukyanovtinkoff.data.model.network

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("films") val films: List<PopularFilmResponse>
)

fun PopularResponse.toDomain() = films.map { it.toDomain() }