package com.example.taller_jetpack.ui.screens.recetasIA

import androidx.lifecycle.ViewModel
import com.example.taller_jetpack.data.model.Receta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecetasCompartidasViewModel @Inject constructor() : ViewModel() {
    private val _recetas = MutableStateFlow<List<Receta>>(emptyList())
    val recetas: StateFlow<List<Receta>> = _recetas

    fun guardarRecetas(lista: List<Receta>) { _recetas.value = lista }
}