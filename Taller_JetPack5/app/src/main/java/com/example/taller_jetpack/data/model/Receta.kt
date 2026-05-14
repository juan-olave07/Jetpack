package com.example.taller_jetpack.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Receta(
    val nombre: String = "",
    @SerialName("tiempo_minutos") val tiempoMinutos: Int = 0,
    val dificultad: String = "",
    val pasos: List<String> = emptyList(),
    val calorias: Int = 0,
    val esFavorita: Boolean = false,
    val id: Long = 0L
)
