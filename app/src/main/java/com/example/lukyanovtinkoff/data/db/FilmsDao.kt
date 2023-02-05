package com.example.lukyanovtinkoff.data.db

import androidx.room.*
import com.example.lukyanovtinkoff.data.model.db.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films")
    fun getAll(): Flow<List<FilmEntity>>

    @Query("SELECT EXISTS(SELECT * FROM films WHERE id = :filmId)")
    suspend fun isFilmSaved(filmId : Int) : Boolean

    @Query("SELECT id FROM films")
    suspend fun getAllIds(): List<Int>

    @Query("SELECT * FROM films WHERE id = :filmId")
    suspend fun getFilm(filmId: Int): FilmEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilm(filmEntity: FilmEntity)

    @Delete
    suspend fun deleteFilm(filmEntity: FilmEntity)
}