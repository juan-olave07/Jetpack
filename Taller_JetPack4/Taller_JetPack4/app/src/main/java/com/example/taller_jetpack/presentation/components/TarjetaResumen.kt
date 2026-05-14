package com.example.taller_jetpack.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taller_jetpack.presentation.theme.ColorGasto
import com.example.taller_jetpack.presentation.theme.ColorIngreso

@Composable
fun TarjetaResumen(
    titulo: String,
    monto: Double,
    esIngreso: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (esIngreso) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.error
    val signo = if (esIngreso) "+" else "-"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$signo $${"%.2f".format(monto)}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
        }
    }
}