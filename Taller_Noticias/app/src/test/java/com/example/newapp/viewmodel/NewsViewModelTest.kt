package com.example.newapp.viewmodel

import com.example.newapp.model.News
import com.example.newapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    private lateinit var viewModel: NewsViewModel
    private lateinit var repository: NewsRepository

    // Dispatcher utilizado para pruebas con corrutinas
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {

        // Reemplaza el dispatcher principal para pruebas
        Dispatchers.setMain(testDispatcher)

        // Crea un repositorio simulado con Mockito
        repository = mock(NewsRepository::class.java)

        // Inicializa el ViewModel usando el repositorio simulado
        viewModel = NewsViewModel(repository)
    }

    @After
    fun tearDown() {

        // Restaura el dispatcher principal original
        Dispatchers.resetMain()
    }

    @Test
    fun getNew_returnsCorrectNewsItem() = runTest {

        // Datos simulados para la prueba
        val testNews = News(
            title = "A",
            content = "B",
            author = "C",
            url = "D",
            urlToImage = "E"
        )

        val newsList = listOf(testNews)

        // Simula la respuesta del repositorio
        `when`(repository.getNewsLocally()).thenReturn(flowOf(newsList))

        // Ejecuta la función a probar
        val result = viewModel.getNew("A")

        // Verifica que el resultado sea el esperado
        assertEquals(testNews, result)
    }
}