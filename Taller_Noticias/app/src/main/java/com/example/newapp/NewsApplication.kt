package com.example.newapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Inicializa Hilt en toda la aplicación para la inyección de dependencias
@HiltAndroidApp
class NewsApplication : Application()