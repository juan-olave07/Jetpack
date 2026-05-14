package com.example.taller_jetpack.data.api

// Importaciones necesarias para usar Room y corrutinas
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Indica que esta interfaz es un DAO (Data Access Object)
// DAO se utiliza para acceder y manipular datos en la base de datos
@Dao
interface CityDao {

    // Consulta SQL que obtiene las últimas 5 ciudades registradas
    // ORDER BY timestamp DESC ordena desde la más reciente
    // LIMIT 5 limita el resultado a solo 5 registros
    @Query("SELECT * FROM city_history ORDER BY timestamp DESC LIMIT 5")

    // Retorna un Flow con una lista de ciudades
    // Flow permite observar cambios en tiempo real
    fun getLastCities(): Flow<List<CityEntity>>

    // Inserta una ciudad en la base de datos
    @Insert(

        // Si ya existe un registro igual, lo reemplaza
        onConflict = OnConflictStrategy.REPLACE
    )

    // Función suspend para ejecutarse dentro de corrutinas
    suspend fun insertCity(city: CityEntity)
}