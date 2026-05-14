package com.example.taller_jetpack.data.api

// Importa el modelo WeatherData
import com.example.taller_jetpack.model.WeatherData

// Importaciones necesarias para Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz que define las peticiones a la API
interface WeatherApiService {

    // Indica que se hará una petición GET al endpoint "weather"
    @GET("weather")

    // Función suspend para realizar peticiones asíncronas
    suspend fun getWeather(

        // Parámetro de consulta para el nombre de la ciudad
        @Query("q") city: String,

        // Parámetro de consulta para la API Key
        @Query("appid") apiKey: String,

        // Parámetro de consulta para las unidades de medida
        // metric devuelve la temperatura en grados Celsius
        @Query("units") units: String = "metric"

    ): WeatherData // Retorna un objeto WeatherData con la respuesta de la API
}

// https://api.openweathermap.org/data/2.5/weather?q=Bogota&appid=123&units=metric
// suspend = asincrono ya ques una peticion