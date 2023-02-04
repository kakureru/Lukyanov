package com.example.lukyanovtinkoff.domain.model

data class Film(
    val id: Int,
    val name: String,
    val year: Int,
    val description: String = "",
    val countries: List<String>,
    val genres: List<String>,
    val posterUrl: String,
    val posterUrlPreview: String,
    val favourite: Boolean = false
)