package com.example.lukyanovtinkoff.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lukyanovtinkoff.data.db.Converters
import com.example.lukyanovtinkoff.domain.model.Film

@Entity(tableName = "films")
@TypeConverters(Converters::class)
data class FilmEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val year: Int,
    val description: String,
    val countries: List<String>,
    val genres: List<String>,
    val posterUrl: String,
    val posterUrlPreview: String,
    val favourite: Boolean
)

fun FilmEntity.toDomain() = Film(
    id = id,
    name = name,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview,
    description = description,
    year = year,
    countries = countries,
    genres = genres,
    favourite = favourite
)