package com.example.taller_jetpack

// Importaciones necesarias para Android y Jetpack Compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.taller_jetpack.ui.theme.Taller_JetPackTheme
import androidx.navigation.compose.*
import com.example.taller_jetpack.model.Tarea
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.taller_jetpack.ui.screen.DetalleScreen
import com.example.taller_jetpack.ui.screen.ListaTareasScreen
import com.example.taller_jetpack.ui.screen.PresentacionScreen
import com.example.taller_jetpack.ui.screen.WeatherScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taller_jetpack.ui.screen.MenuScreen
import dagger.hilt.android.AndroidEntryPoint

// Indica que Hilt puede inyectar dependencias en esta actividad
@AndroidEntryPoint

// Clase principal de la aplicación
class MainActivity : ComponentActivity() {

    // Método que se ejecuta al iniciar la aplicación
    override fun onCreate(savedInstanceState: Bundle?) {


                // Llama al método padre
        super.onCreate(savedInstanceState)

        // Permite que la aplicación use toda la pantalla
        enableEdgeToEdge()

        // Define el contenido visual de la aplicación con Compose
        setContent {

            // Aplica el tema personalizado de la aplicación
            Taller_JetPackTheme {

                // Estado que almacena la lista de tareas
                var tareas by remember {
                    mutableStateOf(listOf<Tarea>())
                }

                //MAPA DE RUTAS

                val navController = rememberNavController()

                NavHost(

                    navController = navController,

                    startDestination = "inicio"
                ) {

                    // Menu
                    composable("inicio") {

                        // Muestra la pantalla del menú principal
                        MenuScreen(navController)
                    }

                    // Taller 1
                    composable("presentacion") {

                        // Muestra la pantalla de presentación
                        PresentacionScreen()
                    }

                    // Taller 2 - Listado
                    composable("lista") {

                        // Muestra la lista de tareas
                        ListaTareasScreen(

                            // Controlador de navegación
                            navController,

                            // Lista actual de tareas
                            tareas,

                            // Actualiza la lista de tareas
                            { tareas = it }
                        )
                    }

                    // Taller 2 - vista detallada
                    composable("detalle/{id}") { backStackEntry ->

                        // Obtiene el ID enviado por navegación
                        val id = backStackEntry.arguments?.getString("id")

                        // Muestra el detalle de la tarea seleccionada
                        DetalleScreen(id, tareas)
                    }

                    // Taller 3 - Tiempo/Clima
                    composable("clima") {

                        // Muestra la pantalla del clima
                        WeatherScreen()
                    }
                }
            }
        }
    }
}