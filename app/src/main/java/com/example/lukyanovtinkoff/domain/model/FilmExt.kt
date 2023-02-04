package com.example.lukyanovtinkoff.domain.model

import com.example.lukyanovtinkoff.data.model.network.Country
import com.example.lukyanovtinkoff.data.model.network.Genre

data class FilmExt (
        val name: String,
        val posterUrl: String,
        val description: String,
        val year: Int,
        val countries: List<String>,
        val genres: List<String>
)
