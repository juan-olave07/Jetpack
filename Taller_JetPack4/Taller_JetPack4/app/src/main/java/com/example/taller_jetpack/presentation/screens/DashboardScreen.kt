package com.example.taller_jetpack.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taller_jetpack.domain.model.Transaccion
import com.example.taller_jetpack.presentation.components.*
import com.example.taller_jetpack.presentation.theme.ColorGasto
import com.example.taller_jetpack.presentation.theme.ColorIngreso

val CATEGORIAS = listOf("Alimentación", "Transporte", "Salud", "Ocio", "Salario", "Otro")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: FinanzasViewModel = hiltViewModel()
) {
    val transacciones by viewModel.transacciones.collectAsStateWithLifecycle()
    val resumen       by viewModel.resumen.collectAsStateWithLifecycle()
    var mostrarDialog by remember { mutableStateOf(false) }
    val snackbarHost  = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.mensaje.collect { snackbarHost.showSnackbar(it) }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHost) },
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Finanzas", style = MaterialTheme.typography.headlineLarge)
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TarjetaResumen(
                        titulo = "INGRESOS",
                        monto = resumen.totalIngresos,
                        esIngreso = true,
                        modifier = Modifier.weight(1f)
                    )
                    TarjetaResumen(
                        titulo = "GASTOS",
                        monto = resumen.totalGastos,
                        esIngreso = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Card(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Resumen del mes", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(12.dp))
                        GraficoBarras(
                            datos = listOf(
                                BarData("Ingresos", resumen.totalIngresos, ColorIngreso),
                                BarData("Gastos",   resumen.totalGastos,   ColorGasto)
                            )
                        )
                    }
                }
            }
            item {
                Text("Movimientos", style = MaterialTheme.typography.titleLarge)
            }
            items(transacciones) { t ->
                TransaccionItem(t, onEliminar = { viewModel.eliminar(t) })
            }
        }
    }

    if (mostrarDialog) {
        AgregarTransaccionDialog(
            onDismiss = { mostrarDialog = false },
            onConfirm = { viewModel.agregar(it); mostrarDialog = false }
        )
    }
}

// ── Subcomponentes internos ──────────────────────────────────────────────────

@Composable
private fun TransaccionItem(t: Transaccion, onEliminar: () -> Unit) {
    val color = if (t.tipo == "INGRESO") ColorIngreso else ColorGasto
    ListItem(
        headlineContent = { Text(t.descripcion) },
        supportingContent = { Text(t.categoria) },
        trailingContent = {
            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                Text(
                    text = "${if (t.tipo == "INGRESO") "+" else "-"} $${"%.2f".format(t.monto)}",
                    color = color,
                    style = MaterialTheme.typography.bodyLarge
                )
                TextButton(onClick = onEliminar) { Text("Eliminar") }
            }
        }
    )
    HorizontalDivider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AgregarTransaccionDialog(
    onDismiss: () -> Unit,
    onConfirm: (Transaccion) -> Unit
) {
    var descripcion    by remember { mutableStateOf("") }
    var monto          by remember { mutableStateOf("") }
    var tipo           by remember { mutableStateOf("INGRESO") }
    var categoriaSelec by remember { mutableStateOf(CATEGORIAS[0]) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva transacción") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = monto,
                    onValueChange = { monto = it },
                    label = { Text("Monto") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("INGRESO", "GASTO").forEach { t ->
                        ChipCategoria(
                            label = t,
                            seleccionado = tipo == t,
                            onClick = { tipo = t }
                        )
                    }
                }
                Text("Categoría", style = MaterialTheme.typography.labelSmall)
                androidx.compose.foundation.layout.FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    CATEGORIAS.forEach { cat ->
                        ChipCategoria(
                            label = cat,
                            seleccionado = categoriaSelec == cat,
                            onClick = { categoriaSelec = cat }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(
                    Transaccion(
                        descripcion = descripcion,
                        monto = monto.toDoubleOrNull() ?: 0.0,
                        tipo = tipo,
                        categoria = categoriaSelec
                    )
                )
            }) { Text("Guardar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}