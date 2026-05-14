package com.example.taller_jetpack.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define la tabla "transacciones" en la base de datos Room
@Entity(tableName = "transacciones")
data class TransaccionEntity (

    // Clave primaria generada automáticamente
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val descripcion: String,
    val monto: Double,

    // Tipo de transacción: INGRESO o GASTO
    val tipo: String,

    val categoria: String,

    // Guarda automáticamente la fecha actual
    val fecha: Long = System.currentTimeMillis()
)