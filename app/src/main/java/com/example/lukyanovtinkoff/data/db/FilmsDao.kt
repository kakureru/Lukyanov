package com.example.lukyanovtinkoff.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lukyanovtinkoff.data.model.db.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films")
    fun getAll(): Flow<List<FilmEntity>>

    @Query("SELECT EXISTS(SELECT * FROM films WHERE id = :filmId)")
    suspend fun isFilmSaved(filmId : Int) : Boolean

    @Query("SELECT * FROM films WHERE id = :filmId")
    suspend fun getFilm(filmId: Int): FilmEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilm(filmEntity: FilmEntity)
}