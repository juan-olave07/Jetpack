package com.example.taller_jetpack.domain.model

// Modelo de datos que representa una transacción financiera
data class Transaccion(

    // Identificador único de la transacción
    val id: Int = 0,

    // Descripción o detalle de la transacción
    val descripcion: String,

    // Valor monetario de la transacción
    val monto: Double,

    // Tipo de transacción: INGRESO o GASTO
    val tipo: String,

    // Categoría a la que pertenece la transacción
    val categoria: String,

    // Fecha de creación de la transacción en milisegundos
    val fecha: Long = System.currentTimeMillis()
)