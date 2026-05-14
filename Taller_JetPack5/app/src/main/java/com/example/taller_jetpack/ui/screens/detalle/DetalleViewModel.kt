package com.example.taller_jetpack.ui.screens.detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taller_jetpack.data.repository.RecetaRepository
import com.example.taller_jetpack.data.model.Receta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(
    private val repository: RecetaRepository
) : ViewModel() {

    val favoritas: StateFlow<List<Receta>> = repository
        .obtenerFavoritas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFavorita(receta: Receta) = viewModelScope.launch {
        repository.guardarFavorita(receta)
    }
}