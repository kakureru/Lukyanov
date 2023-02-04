package com.example.lukyanovtinkoff.data.model.network

import com.google.gson.annotations.SerializedName

data class Genre (
    @SerializedName("genre") val genre: String
)

fun Genre.toDomain() = genre