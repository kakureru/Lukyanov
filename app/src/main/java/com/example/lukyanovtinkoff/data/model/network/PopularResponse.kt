package com.example.lukyanovtinkoff.data.model.network

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("films") val films: List<FilmResponse>
)