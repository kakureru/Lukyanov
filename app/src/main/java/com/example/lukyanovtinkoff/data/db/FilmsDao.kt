package com.example.lukyanovtinkoff.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.lukyanovtinkoff.data.model.db.FilmEntity

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilm(filmEntity: FilmEntity)
}