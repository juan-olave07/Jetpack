package com.example.taller_jetpack.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class RecetaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nombre: String,
    val tiempoMinutos: Int,
    val dificultad: String,
    val pasosJson: String,   // Lista serializada como JSON string
    val calorias: Int,
    val esFavorita: Boolean = true
)