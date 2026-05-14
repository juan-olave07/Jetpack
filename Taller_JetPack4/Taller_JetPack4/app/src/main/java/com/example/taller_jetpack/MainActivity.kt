package com.example.taller_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.taller_jetpack.presentation.theme.FinanzasTheme
import com.example.taller_jetpack.presentation.screens.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint

// Indica que esta actividad utilizará Hilt para la inyección de dependencias
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Método que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {

        // Llama al método onCreate de la clase padre
        super.onCreate(savedInstanceState)

        // Habilita el uso completo de la pantalla
        enableEdgeToEdge()

        // Define el contenido visual usando Jetpack Compose
        setContent {

            // Aplica el tema personalizado de la aplicación
            FinanzasTheme {

                // Muestra la pantalla principal Dashboard
                DashboardScreen()
            }
        }
    }
}