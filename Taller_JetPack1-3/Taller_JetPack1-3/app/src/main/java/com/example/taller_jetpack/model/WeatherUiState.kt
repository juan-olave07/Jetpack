package com.example.taller_jetpack.model

// Sealed class utilizada para manejar los diferentes estados de la UI
sealed class WeatherUiState {

    // Estado de carga
    // Se usa mientras se realiza la petición a la API
    object Loading : WeatherUiState()

    // Estado exitoso
    // Contiene los datos obtenidos del clima
    data class Success(
        val data: WeatherData
    ) : WeatherUiState()

    // Estado de error
    // Contiene el mensaje de error ocurrido
    data class Error(
        val message: String
    ) : WeatherUiState()
}

// manejar el estado de procesamiento de la peticion a la API