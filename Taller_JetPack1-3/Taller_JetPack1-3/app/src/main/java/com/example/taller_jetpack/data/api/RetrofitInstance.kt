package com.example.taller_jetpack.data.api

// Importaciones necesarias para usar Retrofit y Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// singleton: solo existe una instancia en toda la app
object RetrofitInstance {

    // URL base de la API de OpenWeatherMap
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // Crea la instancia de la API solo cuando se necesite
    // by lazy evita crearla innecesariamente
    val api: WeatherApiService by lazy {

        // Construcción de Retrofit
        Retrofit.Builder()

            // Define la URL base
            .baseUrl(BASE_URL)

            // Convierte JSON a objetos Kotlin usando Gson
            .addConverterFactory(GsonConverterFactory.create())

            // Construye la instancia Retrofit
            .build()

            // Crea la implementación de la interfaz WeatherApiService
            .create(WeatherApiService::class.java)
    }
}

// Arma el endpoint con los parametros establecidos en ApiService y
// transforma la salida de JSON a kotlin