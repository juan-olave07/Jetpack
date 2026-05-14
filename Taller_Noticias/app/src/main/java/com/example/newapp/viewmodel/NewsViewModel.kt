package com.example.newapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.model.News
import com.example.newapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel encargado de manejar la lógica de noticias
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    // Estado observable con la lista de noticias almacenadas localmente
    val newsList: StateFlow<List<News>> = repository.getNewsLocally()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun fetchNews(country: String) {

        // Obtiene noticias desde el repositorio de forma asíncrona
        viewModelScope.launch {
            repository.getNews(country)
        }
    }

    suspend fun getNew(title: String): News? {

        // Busca una noticia específica según su título
        return repository.getNewsLocally().first().find { it.title == title }
    }
}