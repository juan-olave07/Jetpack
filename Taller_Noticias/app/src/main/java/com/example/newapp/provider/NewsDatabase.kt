package com.example.newapp.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newapp.model.NewsEntity

// Configuración principal de la base de datos Room
@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    // Proporciona acceso al DAO de noticias
    abstract fun newsDao(): NewsDao
}