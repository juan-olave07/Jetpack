package com.example.newapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {

        // Obtiene el contexto de la aplicación en prueba
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Verifica que el nombre del paquete sea el correcto
        assertEquals("com.example.newapp", appContext.packageName)
    }
}