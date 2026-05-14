package com.example.taller_jetpack.ui.screens.ingredientes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun IngredientesScreen(
    ingredientesIniciales: List<String>,
    onGenerarRecetas: (List<String>) -> Unit,
    viewModel: IngredientesViewModel
) {
    LaunchedEffect(ingredientesIniciales) {
        viewModel.inicializar(ingredientesIniciales)
    }

    val ingredientes by viewModel.ingredientes.collectAsStateWithLifecycle()
    var nuevoIngrediente by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            Surface(tonalElevation = 4.dp) {
                Button(
                    onClick = { onGenerarRecetas(ingredientes) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = ingredientes.isNotEmpty()
                ) {
                    Text("Generar recetas con IA ✨")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Ingredientes detectados", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = nuevoIngrediente,
                    onValueChange = { nuevoIngrediente = it },
                    label = { Text("Agregar ingrediente") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                IconButton(onClick = {
                    if (nuevoIngrediente.isNotBlank()) {
                        viewModel.agregar(nuevoIngrediente.trim())
                        nuevoIngrediente = ""
                    }
                }) {
                    Icon(Icons.Default.Add, "Agregar")
                }
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(ingredientes, key = { it }) { ingrediente ->
                    ListItem(
                        headlineContent = { Text(ingrediente) },
                        trailingContent = {
                            IconButton(onClick = { viewModel.eliminar(ingrediente) }) {
                                Icon(Icons.Default.Close, "Eliminar")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}