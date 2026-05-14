package com.example.taller_jetpack.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
data class BarData(val etiqueta: String, val valor: Double, val color: Color)

@Composable
fun GraficoBarras(
    datos: List<BarData>,
    modifier: Modifier = Modifier,
    alturaMaxDp: Float = 150f
) {
    val maxValor = datos.maxOfOrNull { it.valor }?.takeIf { it > 0 } ?: 1.0

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(alturaMaxDp.dp + 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        datos.forEach { bar ->
            val fraccion = (bar.valor / maxValor).coerceIn(0.0, 1.0)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height((fraccion * alturaMaxDp).dp)
                        .background(bar.color, RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = bar.etiqueta,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}