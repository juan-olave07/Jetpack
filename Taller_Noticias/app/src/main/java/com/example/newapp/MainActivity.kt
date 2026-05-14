package com.example.newapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.newapp.ui.NewsApp
import com.example.newapp.ui.theme.NewAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define la interfaz principal usando Jetpack Compose
        setContent {

            // Aplica el tema personalizado de la aplicación
            NewAppTheme {

                // Contenedor principal de la interfaz
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // Muestra la aplicación de noticias
                    NewsApp()
                }
            }
        }
    }
}