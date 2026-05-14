package com.example.taller_jetpack.ui.screens.recetasIA

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taller_jetpack.data.model.Receta
import kotlinx.coroutines.delay

@Composable
fun RecetasIAScreen(
    ingredientes: List<String>,
    onVerDetalle: (Int) -> Unit,
    viewModel: RecetasIAViewModel,
    onRecetasListas: (List<Receta>) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.recetas) {
        if (state.recetas.isNotEmpty()) {
            onRecetasListas(state.recetas)
        }
    }

    LaunchedEffect(ingredientes) {
        viewModel.generarRecetas(ingredientes)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            )
    ) {
        // Fondo con círculos borrosos (efecto glassmorphism)
        FondoBlur()

        when {
            state.error != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                }
            }

            state.recetas.isNotEmpty() -> {
                // Muestra tarjetas de recetas parseadas
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            "🍽️ Tus recetas",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    itemsIndexed(state.recetas) { index, receta ->
                        RecetaCard(
                            receta = receta,
                            index = index,
                            guardando = index in state.guardando,
                            onGuardar = { viewModel.guardarFavorita(index) },
                            onVerDetalle = { onVerDetalle(index) }
                        )
                    }
                }
            }

            state.cargando -> {
                // Efecto typewriter mientras hace streaming
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "✨ Generando recetas...",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(16.dp))

                    TypewriterText(
                        textoCompleto = state.textoStreaming,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                            )
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FondoBlur() {
    val infiniteTransition = rememberInfiniteTransition(label = "blur_anim")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 30f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = "offset"
    )
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .size(250.dp)
                .offset(x = (50 + offset).dp, y = (80 + offset / 2).dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    RoundedCornerShape(50)
                )
                .blur(60.dp)
        )
        Box(
            Modifier
                .size(200.dp)
                .offset(x = (160 - offset).dp, y = (400 + offset).dp)
                .background(
                    MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
                    RoundedCornerShape(50)
                )
                .blur(60.dp)
        )
    }
}

// ─── Efecto Typewriter ────────────────────────────────────────────────────────

@Composable
fun TypewriterText(
    textoCompleto: String,
    modifier: Modifier = Modifier,
    velocidadMs: Long = 15L
) {
    var textoMostrado by remember { mutableStateOf("") }

    // Sincroniza carácter a carácter cuando llega nuevo texto en streaming
    LaunchedEffect(textoCompleto) {
        if (textoCompleto.length > textoMostrado.length) {
            for (i in textoMostrado.length until textoCompleto.length) {
                textoMostrado = textoCompleto.substring(0, i + 1)
                delay(velocidadMs)
            }
        }
    }

    // Cursor parpadeante
    val cursorAlpha by rememberInfiniteTransition(label = "cursor").animateFloat(
        initialValue = 1f, targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse),
        label = "cursorAlpha"
    )

    Text(
        text = textoMostrado + "▋",
        modifier = modifier,
        fontFamily = FontFamily.Monospace,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = cursorAlpha)
                .let { MaterialTheme.colorScheme.onSurfaceVariant } // texto normal
        )
    )
}

// ─── Tarjeta de receta con chips y botón animado ──────────────────────────────

@Composable
fun RecetaCard(
    receta: Receta,
    index: Int,
    guardando: Boolean,
    onGuardar: () -> Unit,
    onVerDetalle: () -> Unit
) {
    val escala by animateFloatAsState(
        targetValue = if (guardando) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "boton_escala"
    )

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    receta.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Botón guardar animado
                IconButton(
                    onClick = onGuardar,
                    modifier = Modifier.size((40 * escala).dp)
                ) {
                    AnimatedContent(
                        targetState = guardando,
                        label = "icono_guardar"
                    ) { guardandoActual ->
                        if (guardandoActual) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        } else {
                            Icon(
                                imageVector = Icons.Default.BookmarkBorder,
                                contentDescription = "Guardar favorita",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Chips de tiempo y dificultad
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipInfo(
                    icono = { Icon(Icons.Default.Schedule, null, Modifier.size(14.dp)) },
                    etiqueta = "${receta.tiempoMinutos} min"
                )
                ChipInfo(
                    icono = { Icon(Icons.Default.SignalCellularAlt, null, Modifier.size(14.dp)) },
                    etiqueta = receta.dificultad
                )
                AssistChip(
                    onClick = {},
                    label = { Text("🔥 ${receta.calorias} kcal", fontSize = 12.sp) }
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onVerDetalle,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver receta completa →")
            }
        }
    }
}

@Composable
fun ChipInfo(icono: @Composable () -> Unit, etiqueta: String) {
    AssistChip(
        onClick = {},
        label = { Text(etiqueta, fontSize = 12.sp) },
        leadingIcon = icono
    )
}