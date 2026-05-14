package com.example.taller_jetpack.ui.components

// Importaciones necesarias para usar componentes Compose
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// Composable reutilizable para mostrar información en formato Chip
@Composable
fun InfoChip(label: String, value: String) {

    // AssistChip muestra un pequeño contenedor interactivo
    AssistChip(

        // Acción al hacer clic
        // En este caso no realiza ninguna acción
        onClick = {},

        // Texto mostrado dentro del chip
        label = {

            // Concatena el label y el valor
            Text("$label: $value")
        }
    )
}