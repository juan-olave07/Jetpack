package com.example.taller_jetpack.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.taller_jetpack.model.WeatherUiState
import com.example.taller_jetpack.ui.components.WeatherContent
import com.example.taller_jetpack.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val history by viewModel.history.collectAsState()
    var city by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadWeather("Bogota")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Buscador
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Buscar ciudad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.loadWeather(city)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  Historial
        LazyRow {
            items(history) { city ->
                AssistChip(
                    onClick = {
                        viewModel.loadWeather(city.name)
                    },
                    label = { Text(city.name) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Estado UI
        when (uiState) {
            is WeatherUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is WeatherUiState.Success -> {
                val data = (uiState as WeatherUiState.Success).data
                WeatherContent(data)
            }

            is WeatherUiState.Error -> {
                val message = (uiState as WeatherUiState.Error).message
                Text(
                    text = mapWeatherError(message),
                    color = Color.Red
                )
            }
        }
    }
}

fun mapWeatherError(message: String): String {
    return when {
        message.contains("404") -> "Ciudad no encontrada. Verifica el nombre."
        message.contains("401") -> "Error de API. Revisa tu clave."
        message.contains("timeout", ignoreCase = true) -> "Tiempo de espera agotado. Intenta de nuevo."
        else -> "Ocurrió un error inesperado."
    }
}