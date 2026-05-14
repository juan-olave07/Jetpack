package com.example.taller_jetpack.ui.components

// Importaciones necesarias para animaciones, layouts y componentes Compose
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.Brush
import com.example.taller_jetpack.model.WeatherData

// Composable principal que muestra la información del clima
@Composable
fun WeatherContent(data: WeatherData) {

    // Obtiene la condición principal del clima
    // Si no existe, usa "Clear" por defecto
    val condition = data.weather.firstOrNull()?.main ?: "Clear"

    // Obtiene el código del icono del clima
    // Si no existe, usa "01d" por defecto
    val iconCode = data.weather.firstOrNull()?.icon ?: "01d"

    // Verifica si es de noche
    // Los iconos nocturnos terminan con "n"
    val isNight = iconCode.endsWith("n")

    // Animación del color de fondo
    val baseColor by animateColorAsState(

        // Cambia el color según el clima
        targetValue = when {

            // Azul si está lloviendo
            condition == "Rain" -> Color(0xFF1565C0)

            // Morado si está despejado y es de noche
            condition == "Clear" && isNight -> Color(0xFF4A148C)

            // Amarillo si está despejado de día
            condition == "Clear" -> Color(0xFFFFA000)

            // Verde para otros estados
            else -> Color(0xFF2F8F47)
        },

        label = "bg"
    )

    // Gradiente vertical para el fondo
    val gradient = Brush.verticalGradient(

        // Lista de colores usados en el gradiente
        colors = listOf(

            // Color principal
            baseColor,

            // Color con transparencia
            baseColor.copy(alpha = 0.7f)
        )
    )

    // Box usado como contenedor principal
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(gradient) // Aplica el fondo degradado
            .padding(24.dp) // Espaciado interno
    ) {

        // Column organiza el contenido verticalmente
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Muestra el nombre de la ciudad
            Text(
                text = data.name,
                style = MaterialTheme.typography.displayLarge,
                color = Color.White
            )

            // 🌡 Temperatura
            Text(

                // Convierte la temperatura a entero y agrega el símbolo °
                text = "${data.main.temp.toInt()}°",

                style = MaterialTheme.typography.displayLarge,
                color = Color.White
            )

            // Espacio vertical
            Spacer(modifier = Modifier.height(16.dp))

            // Muestra el icono del clima
            WeatherIcon(condition, isNight)

            // Espacio vertical
            Spacer(modifier = Modifier.height(24.dp))

            // 💧🌬 Chips
            Row {

                // Chip que muestra la humedad
                InfoChip(
                    "Humedad",
                    "${data.main.humidity}%"
                )

                // Espacio horizontal
                Spacer(modifier = Modifier.width(8.dp))

                // Chip que muestra la velocidad del viento
                InfoChip(
                    "Viento",
                    "${data.wind.speed} m/s"
                )
            }
        }
    }
}

// Composable que muestra el icono según el clima
@Composable
fun WeatherIcon(condition: String, isNight: Boolean) {

    // Selecciona el icono según la condición climática
    val icon = when (condition) {

        // Icono para lluvia
        "Rain" -> Icons.Default.Star

        // Si está despejado:
        // muestra luna en la noche o sol en el día
        "Clear" -> if (isNight)
            Icons.Default.Nightlight
        else
            Icons.Default.WbSunny

        // Icono por defecto para otros climas
        else -> Icons.Default.Cloud
    }

    // Muestra el icono seleccionado
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(80.dp)
    )
}