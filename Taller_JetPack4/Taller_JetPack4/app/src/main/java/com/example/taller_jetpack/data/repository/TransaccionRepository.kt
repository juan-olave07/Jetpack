package com.example.taller_jetpack.data.repository

import com.example.taller_jetpack.domain.model.Transaccion
import kotlinx.coroutines.flow.Flow

interface TransaccionRepository {
    fun getAll(): Flow<List<Transaccion>>
    suspend fun getTotalPorTipo(tipo: String): Double
    suspend fun insertar(transaccion: Transaccion)
    suspend fun eliminar(transaccion: Transaccion)
}