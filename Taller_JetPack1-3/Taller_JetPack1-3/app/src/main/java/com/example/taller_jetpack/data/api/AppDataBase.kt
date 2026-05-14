package com.example.taller_jetpack.data.api

// Importaciones necesarias para usar Room Database
import androidx.room.Database
import androidx.room.RoomDatabase

// Define la base de datos de Room
@Database(

    // Lista de entidades (tablas) que tendrá la base de datos
    entities = [CityEntity::class],

    // Versión actual de la base de datos
    version = 1
)

// Clase abstracta que hereda de RoomDatabase
abstract class AppDatabase : RoomDatabase() {

    // Método abstracto que devuelve el DAO de ciudades
    // Permite acceder a las operaciones de la tabla CityEntity
    abstract fun cityDao(): CityDao
}