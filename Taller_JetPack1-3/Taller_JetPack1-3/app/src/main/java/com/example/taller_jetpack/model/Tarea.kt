package com.example.taller_jetpack.model

// Data class que representa una tarea
data class Tarea (

    // Identificador único de la tarea
    val id: Int,

    // Título o nombre de la tarea
    val titulo: String,

    // Indica si la tarea está completada o no
    val completada: Boolean
)