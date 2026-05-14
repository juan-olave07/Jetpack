package com.example.taller_jetpack.di

// Importaciones necesarias para Hilt y Retrofit
import com.example.taller_jetpack.data.api.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Indica que esta clase es un módulo de Hilt
@Module

// Define que las dependencias existirán durante toda la aplicación
@InstallIn(SingletonComponent::class)

// Objeto encargado de proveer dependencias de red
object ModuloRed {

    // URL base de la API del clima
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // Provee una instancia de Retrofit
    @Provides

    // Singleton asegura una sola instancia de Retrofit
    @Singleton
    fun provideRetrofit(): Retrofit =

        // Construcción de Retrofit
        Retrofit.Builder()

            // Define la URL base
            .baseUrl(BASE_URL)

            // Convierte JSON a objetos Kotlin usando Gson
            .addConverterFactory(GsonConverterFactory.create())

            // Construye la instancia Retrofit
            .build()

    // Provee una implementación de la API
    @Provides
    fun provideApi(retrofit: Retrofit): WeatherApiService =

        // Crea la implementación de WeatherApiService
        retrofit.create(WeatherApiService::class.java)
}