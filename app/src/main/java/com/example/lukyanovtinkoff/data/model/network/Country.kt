package com.example.lukyanovtinkoff.data.model.network

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country") val country: String
)

fun Country.toDomain() = country