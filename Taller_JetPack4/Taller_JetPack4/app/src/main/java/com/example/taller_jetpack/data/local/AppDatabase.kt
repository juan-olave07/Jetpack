package com.example.taller_jetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

// Configuración principal de la base de datos Room
@Database(
    entities = [TransaccionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Proporciona acceso al DAO de transacciones
    abstract fun transaccionDao(): TransaccionDao
}