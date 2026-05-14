package com.example.taller_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.taller_jetpack.ui.navigation.AppNavGraph
import com.example.taller_jetpack.ui.theme.Taller_JetPack5Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Taller_JetPack5Theme {
                AppNavGraph()
            }
        }
    }
}