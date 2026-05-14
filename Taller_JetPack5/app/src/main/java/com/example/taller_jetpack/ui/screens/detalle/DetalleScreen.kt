package com.example.taller_jetpack.ui.screens.detalle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taller_jetpack.data.model.Receta

// Nota: Las recetas del detalle se obtienen del ViewModel compartido de RecetasIA.
// Para simplificar el taller, usaremos un objeto singleton o SavedStateHandle.
// En producción se pasarían mediante un repositorio en memoria o SharedViewModel.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    recetaIndex: Int,
    onVolver: () -> Unit,
    receta: Receta, // pasada desde el NavGraph via ViewModel compartido
    viewModel: DetalleViewModel
) {
    val favoritas by viewModel.favoritas.collectAsStateWithLifecycle()
    val esFavorita = favoritas.any { it.nombre == receta.nombre }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(receta.nombre) },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleFavorita(receta) }) {
                        Icon(
                            imageVector = if (esFavorita) Icons.Default.Bookmark
                            else Icons.Default.BookmarkBorder,
                            contentDescription = "Favorita",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(onClick = {}, label = { Text("⏱ ${receta.tiempoMinutos} min") })
                    AssistChip(onClick = {}, label = { Text("📊 ${receta.dificultad}") })
                    AssistChip(onClick = {}, label = { Text("🔥 ${receta.calorias} kcal") })
                }
            }
            item {
                Text(
                    "Pasos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            itemsIndexed(receta.pasos) { i, paso ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
                            Text("${i + 1}", fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(paso, style = MaterialTheme.typography.bodyLarge)
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}