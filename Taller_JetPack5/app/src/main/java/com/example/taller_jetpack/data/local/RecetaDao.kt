package com.example.taller_jetpack.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetaDao {
    @Query("SELECT * FROM recetas ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<RecetaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(receta: RecetaEntity): Long

    @Query("DELETE FROM recetas WHERE id = :id")
    suspend fun eliminar(id: Long)
}