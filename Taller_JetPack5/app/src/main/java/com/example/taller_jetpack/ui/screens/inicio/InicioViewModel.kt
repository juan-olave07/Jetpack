package com.example.taller_jetpack.ui.screens.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taller_jetpack.data.model.Receta
import com.example.taller_jetpack.data.repository.RecetaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InicioViewModel @Inject constructor(
    repository: RecetaRepository
) : ViewModel() {

    val favoritas: StateFlow<List<Receta>> = repository
        .obtenerFavoritas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}