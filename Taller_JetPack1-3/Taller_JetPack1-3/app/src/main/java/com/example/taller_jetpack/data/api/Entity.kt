package com.example.taller_jetpack.data.api

// Importaciones necesarias para usar entidades de Room
import androidx.room.Entity
import androidx.room.PrimaryKey

// Define esta clase como una tabla de la base de datos
// El nombre de la tabla será "city_history"
@Entity(tableName = "city_history")

// Data class que representa una ciudad guardada
data class CityEntity(

    // Clave primaria de la tabla
    // autoGenerate = true permite generar IDs automáticamente
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Nombre de la ciudad
    val name: String,

    // Fecha y hora en la que se guardó la ciudad
    // System.currentTimeMillis() obtiene el tiempo actual en milisegundos
    val timestamp: Long = System.currentTimeMillis()
)