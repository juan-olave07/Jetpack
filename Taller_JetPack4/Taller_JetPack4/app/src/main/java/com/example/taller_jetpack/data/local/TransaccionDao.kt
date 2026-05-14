package com.example.taller_jetpack.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// DAO encargado de las operaciones sobre la tabla de transacciones
@Dao
interface TransaccionDao {

    // Obtiene todas las transacciones ordenadas por fecha descendente
    @Query("SELECT * FROM transacciones ORDER BY fecha DESC")
    fun getAll(): Flow<List<TransaccionEntity>>

    // Calcula el total según el tipo de transacción
    @Query("SELECT SUM(monto) FROM transacciones WHERE tipo = :tipo")
    suspend fun getTotalPorTipo(tipo: String): Double?

    // Inserta una transacción en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(t: TransaccionEntity)

    // Elimina una transacción de la base de datos
    @Delete
    suspend fun eliminar(t: TransaccionEntity)
}