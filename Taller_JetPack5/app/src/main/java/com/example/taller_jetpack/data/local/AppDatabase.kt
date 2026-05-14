package com.example.taller_jetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecetaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recetaDao(): RecetaDao
}