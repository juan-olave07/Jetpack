package com.example.taller_jetpack.model

// Data class principal que representa toda la información del clima
data class WeatherData(

    // Nombre de la ciudad consultada
    val name: String,

    // Contiene información principal como temperatura y humedad
    val main: Main,

    // Lista con las condiciones climáticas
    val weather: List<Weather>,

    // Información relacionada con el viento
    val wind: Wind
)

// Data class que almacena datos principales del clima
data class Main(

    // Temperatura actual de la ciudad
    val temp: Double,

    // Porcentaje de humedad
    val humidity: Int
)

// Data class que representa el estado del clima
data class Weather(

    // Estado general del clima
    // Ejemplo: Rain, Clouds, Clear
    val main: String,

    // Descripción detallada del clima
    // Ejemplo: light rain
    val description: String,

    // Código del icono proporcionado por la API
    val icon: String?
)

// Data class que contiene información del viento
data class Wind(

    // Velocidad del viento
    val speed: Double
)
