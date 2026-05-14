package com.example.taller_jetpack.data.repository

// Importaciones necesarias para el repositorio
import com.example.taller_jetpack.data.api.CityDao
import com.example.taller_jetpack.data.api.CityEntity
import com.example.taller_jetpack.data.api.RetrofitInstance
import com.example.taller_jetpack.data.api.WeatherApiService
import com.example.taller_jetpack.model.WeatherData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

// Repositorio encargado de manejar la lógica de datos
class WeatherRepository @Inject constructor(

    // Servicio de la API del clima
    private val api: WeatherApiService,

    // DAO para acceder a la base de datos
    private val dao: CityDao
) {

    // Obtiene el historial de ciudades guardadas
    fun getHistory(): Flow<List<CityEntity>> {

        // Retorna las últimas ciudades almacenadas
        return dao.getLastCities()
    }

    // Guarda una ciudad en la base de datos
    suspend fun saveCity(city: String) {

        // Inserta una nueva entidad CityEntity
        dao.insertCity(
            CityEntity(name = city)
        )
    }

    // Obtiene la información del clima de una ciudad
    suspend fun getWeather(city: String): WeatherData {

        // Llama a la API enviando la ciudad y la API Key
        return api.getWeather(
            city,
            "5a5a78f416188d6129485a27b675ef52"
        )
    }
}

// Al igual que otros repositorios, este tiene la logica fuerte de ejecucion
// En este caso tiene la Api Key y la consulta propiamente a la api.