package com.example.lukyanovtinkoff.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lukyanovtinkoff.data.model.db.FilmEntity

@Database(entities = [FilmEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun FilmsDao(): FilmsDao
}