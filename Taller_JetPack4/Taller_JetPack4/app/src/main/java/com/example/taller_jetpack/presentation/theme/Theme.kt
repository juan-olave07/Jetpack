package com.example.taller_jetpack.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary          = Verde500,
    onPrimary        = Neutro50,
    secondary        = Azul700,
    error            = Rojo500,
    background       = Neutro50,
    surface          = Neutro50,
    onBackground     = Neutro900,
    onSurface        = Neutro900,
)

private val DarkColors = darkColorScheme(
    primary          = Verde200,
    onPrimary        = Neutro900,
    secondary        = Rojo200,
    error            = Rojo200,
    background       = Neutro900,
    surface          = Neutro900,
    onBackground     = Neutro50,
    onSurface        = Neutro50,
)

@Composable
fun FinanzasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,       // ponlo en true si quieres Material You
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else      -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = FinanzasTypography,
        shapes      = FinanzasShapes,
        content     = content
    )
}