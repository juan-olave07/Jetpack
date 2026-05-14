package com.example.taller_jetpack.ui.screen

// Importaciones necesarias para animaciones, componentes y layouts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taller_jetpack.model.Tarea

// Pantalla principal de la lista de tareas
@Composable
fun ListaTareasScreen(
    navController: NavController,
    tareas: List<Tarea>,
    onTareasChange: (List<Tarea>) -> Unit
) {

    // Estado para mostrar u ocultar el dialogo
    var mostrarDialogo by remember { mutableStateOf(false) }

    // Estado para almacenar el texto ingresado
    var texto by remember { mutableStateOf("") }

    // Estado para validar errores en el campo
    var esError by remember { mutableStateOf(false) }

    // Scaffold organiza la estructura general de la pantalla
    Scaffold(

        // Botón flotante para agregar tareas
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogo = true }) {

                // Icono del botón flotante
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { padding ->

        // LazyColumn muestra una lista desplazable
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // Ocupa toda la pantalla
                .padding(padding) // Padding interno del Scaffold
                .padding(16.dp) // Margen adicional
        ) {

            // Recorre todas las tareas de la lista
            items(tareas, key = { it.id }) { tarea ->

                // Composable que representa cada tarea
                TareaItem(
                    tarea = tarea,

                    // Cambia el estado de completada
                    onCheckedChange = { checked ->
                        onTareasChange(
                            tareas.map {

                                // Actualiza únicamente la tarea seleccionada
                                if (it.id == tarea.id)
                                    it.copy(completada = checked)
                                else
                                    it
                            }
                        )
                    },

                    // Elimina una tarea de la lista
                    onDelete = {
                        onTareasChange(
                            tareas.filter { it.id != tarea.id }
                        )
                    },

                    // Navega a la pantalla detalle
                    onClick = {
                        navController.navigate("detalle/${tarea.id}")
                    }
                )
            }
        }
    }

    // Verifica si el dialogo debe mostrarse
    if (mostrarDialogo) {

        // Ventana emergente para agregar tareas
        AlertDialog(

            // Acción al cerrar el dialogo
            onDismissRequest = { mostrarDialogo = false },

            // Título del dialogo
            title = {
                Text("Nueva tarea")
            },

            // Contenido principal del dialogo
            text = {

                // Column organiza elementos verticalmente
                Column {

                    // Campo de texto para escribir la tarea
                    TextField(
                        value = texto,

                        // Actualiza el texto mientras el usuario escribe
                        onValueChange = {
                            texto = it

                            // Valida si el campo está vacío
                            esError = it.isBlank()
                        },

                        // Etiqueta del campo
                        label = { Text("Título") },

                        // Indica si existe error
                        isError = esError,

                        // Permite una sola línea
                        singleLine = true
                    )

                    // Muestra mensaje de error
                    if (esError) {
                        Text(
                            text = "El título no puede estar vacío",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            },

            // Botón para confirmar y agregar tarea
            confirmButton = {
                TextButton(

                    // Acción al presionar el botón
                    onClick = {

                        // Agrega una nueva tarea a la lista
                        onTareasChange(
                            tareas + Tarea(

                                // Genera un ID automáticamente
                                id = (tareas.maxOfOrNull { it.id } ?: 0) + 1,

                                // Título ingresado
                                titulo = texto,

                                // Estado inicial
                                completada = false
                            )
                        )

                        // Limpia el texto
                        texto = ""

                        // Reinicia el estado de error
                        esError = false

                        // Cierra el dialogo
                        mostrarDialogo = false
                    },

                    // El botón solo funciona si hay texto
                    enabled = texto.isNotBlank()
                ) {

                    // Texto del botón
                    Text("Agregar")
                }
            },

            // Botón para cancelar
            dismissButton = {
                TextButton(

                    // Cierra el dialogo
                    onClick = {
                        mostrarDialogo = false
                    }
                ) {

                    // Texto del botón
                    Text("Cancelar")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaItem(
    tarea: Tarea,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {

    // Animación de transparencia
    val alpha by animateFloatAsState(

        // Si la tarea está completada se vuelve más transparente
        targetValue = if (tarea.completada) 0.5f else 1f,

        label = "fade"
    )

    // Estado del gesto Swipe
    val dismissState = rememberSwipeToDismissBoxState()

    // Reinicia el estado del Swipe cuando cambia la tarea
    LaunchedEffect(tarea.id) {
        dismissState.snapTo(SwipeToDismissBoxValue.Settled)
    }

    // Detecta si el usuario deslizó para eliminar
    LaunchedEffect(dismissState.currentValue) {

        // Si se desliza de derecha a izquierda
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {

            // Elimina la tarea
            onDelete()
        }
    }

    // Contenedor que permite deslizar para eliminar
    SwipeToDismissBox(
        state = dismissState,

        // Deshabilita el swipe de izquierda a derecha
        enableDismissFromStartToEnd = false,

        // Fondo que aparece al deslizar
        backgroundContent = {

            // Box organiza el icono de eliminar
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {

                // Icono de eliminar
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    ) {

        // Row que contiene los elementos de la tarea
        Row(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .clickable { onClick() } // Detecta clics
                .padding(12.dp) // Espaciado interno
                .alpha(alpha), // Aplica transparencia animada
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Checkbox para marcar completada
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = onCheckedChange
            )

            // Espacio horizontal
            Spacer(modifier = Modifier.width(8.dp))

            // Texto de la tarea
            Text(
                text = tarea.titulo,
                style = MaterialTheme.typography.bodyLarge,

                // Tacha el texto si está completada
                textDecoration = if (tarea.completada)
                    TextDecoration.LineThrough
                else
                    TextDecoration.None,

                // Hace que el texto ocupe el espacio restante
                modifier = Modifier.weight(1f)
            )

            // Botón de eliminar
            IconButton(onClick = onDelete) {

                // Icono de eliminar
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}

// Pantalla de detalle de una tarea
@Composable
fun DetalleScreen(id: String?, tareas: List<Tarea>) {

    // Busca la tarea según el ID recibido
    val tarea = tareas.find { it.id.toString() == id }

    // Column organiza la información verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .padding(24.dp), // Margen interno
        verticalArrangement = Arrangement.Center // Centra el contenido
    ) {

        // Muestra el ID de la tarea
        Text("Id: ${tarea?.id}")

        // Espacio entre elementos
        Spacer(modifier = Modifier.height(12.dp))

        // Muestra el título de la tarea
        Text("Título: ${tarea?.titulo}")

        // Espacio entre elementos
        Spacer(modifier = Modifier.height(12.dp))

        // Muestra el estado de completada
        Text("Completada: ${tarea?.completada}")
    }
}