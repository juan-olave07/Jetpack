package com.example.taller_jetpack.data.repository

import com.example.taller_jetpack.data.local.TransaccionDao
import com.example.taller_jetpack.domain.model.Transaccion
import com.example.taller_jetpack.data.local.TransaccionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Implementación del repositorio encargado de conectar la lógica con la base de datos
class TransaccionRepositoryImpl @Inject constructor(
    private val dao: TransaccionDao
) : TransaccionRepository {

    // Obtiene todas las transacciones y las convierte al modelo de dominio
    override fun getAll(): Flow<List<Transaccion>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun getTotalPorTipo(tipo: String): Double =
        dao.getTotalPorTipo(tipo) ?: 0.0

    // Inserta una transacción en la base de datos
    override suspend fun insertar(transaccion: Transaccion) =
        dao.insertar(transaccion.toEntity())

    // Elimina una transacción de la base de datos
    override suspend fun eliminar(transaccion: Transaccion) =
        dao.eliminar(transaccion.toEntity())
}

// Convierte una entidad de base de datos al modelo de dominio
private fun TransaccionEntity.toDomain() = Transaccion(
    id = id, descripcion = descripcion, monto = monto,
    tipo = tipo, categoria = categoria, fecha = fecha
)

// Convierte el modelo de dominio en entidad para la base de datos
private fun Transaccion.toEntity() = TransaccionEntity(
    id = id, descripcion = descripcion, monto = monto,
    tipo = tipo, categoria = categoria, fecha = fecha
)