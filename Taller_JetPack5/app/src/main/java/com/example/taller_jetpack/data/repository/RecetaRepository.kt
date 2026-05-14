package com.example.taller_jetpack.data.repository

import com.example.taller_jetpack.data.local.RecetaDao
import com.example.taller_jetpack.data.local.RecetaEntity
import com.example.taller_jetpack.data.model.Receta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecetaRepository @Inject constructor(
    private val dao: RecetaDao
) {
    fun obtenerFavoritas(): Flow<List<Receta>> =
        dao.obtenerTodas().map { lista ->
            lista.map { entity ->
                Receta(
                    id = entity.id,
                    nombre = entity.nombre,
                    tiempoMinutos = entity.tiempoMinutos,
                    dificultad = entity.dificultad,
                    pasos = Json.decodeFromString(entity.pasosJson),
                    calorias = entity.calorias,
                    esFavorita = entity.esFavorita
                )
            }
        }

    suspend fun guardarFavorita(receta: Receta) {
        dao.insertar(
            RecetaEntity(
                nombre = receta.nombre,
                tiempoMinutos = receta.tiempoMinutos,
                dificultad = receta.dificultad,
                pasosJson = Json.encodeToString(receta.pasos),
                calorias = receta.calorias
            )
        )
    }
}