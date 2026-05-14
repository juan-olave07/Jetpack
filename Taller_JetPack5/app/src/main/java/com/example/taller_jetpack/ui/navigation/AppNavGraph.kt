package com.example.taller_jetpack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taller_jetpack.ui.screens.camara.CamaraScreen
import com.example.taller_jetpack.ui.screens.detalle.DetalleScreen
import com.example.taller_jetpack.ui.screens.ingredientes.IngredientesScreen
import com.example.taller_jetpack.ui.screens.inicio.InicioScreen
import com.example.taller_jetpack.ui.screens.recetasIA.RecetasCompartidasViewModel
import com.example.taller_jetpack.ui.screens.recetasIA.RecetasIAScreen

sealed class Ruta(val path: String) {
    data object Inicio : Ruta("inicio")
    data object Camara : Ruta("camara")
    data object Ingredientes : Ruta("ingredientes")
    data object RecetasIA : Ruta("recetasIA")
    data object Detalle : Ruta("detalle/{recetaIndex}") {
        fun crearRuta(index: Int) = "detalle/$index"
    }
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    val recetasCompartidas: RecetasCompartidasViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Ruta.Inicio.path) {

        composable(Ruta.Inicio.path) { entry ->
            InicioScreen(
                onIrACamara = { navController.navigate(Ruta.Camara.path) },
                viewModel = hiltViewModel(entry)
            )
        }

        composable(Ruta.Camara.path) {entry ->
            CamaraScreen(
                onIngredientesDetectados = { ingredientes ->
                    // Pasamos ingredientes via SavedStateHandle o ViewModel compartido
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("ingredientes", ingredientes.toTypedArray())
                    navController.navigate(Ruta.Ingredientes.path)
                },
                viewModel = hiltViewModel(entry)
            )
        }

        composable(Ruta.Ingredientes.path) {
                entry ->
                IngredientesScreen(
                    ingredientesIniciales = navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<Array<String>>("ingredientes")
                        ?.toList() ?: emptyList(),
                    onGenerarRecetas = { lista ->
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("ingredientesFinales", lista.toTypedArray())
                        navController.navigate(Ruta.RecetasIA.path)
                    },
                    viewModel = hiltViewModel(entry)
                )
        }

        composable(Ruta.RecetasIA.path) { entry ->
                RecetasIAScreen(
                    ingredientes = navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<Array<String>>("ingredientesFinales")
                        ?.toList() ?: emptyList(),
                    onVerDetalle = { index ->
                        navController.navigate(Ruta.Detalle.crearRuta(index))
                    },
                    viewModel = hiltViewModel(entry),
                    onRecetasListas = { lista ->
                        recetasCompartidas.guardarRecetas(lista)   // <-- guarda aquí
                    }
                )
        }

        composable(
            route = Ruta.Detalle.path,
            arguments = listOf(navArgument("recetaIndex") { type = NavType.IntType })
        ) { entry ->
            val index = entry.arguments?.getInt("recetaIndex") ?: 0
            val recetas by recetasCompartidas.recetas.collectAsStateWithLifecycle()
            val receta = recetas.getOrNull(index)
            if (receta != null) {
                DetalleScreen(
                    recetaIndex = index,
                    onVolver = { navController.popBackStack() },
                    receta = receta,
                    viewModel = hiltViewModel(entry)
                )
            }
        }
    }
}