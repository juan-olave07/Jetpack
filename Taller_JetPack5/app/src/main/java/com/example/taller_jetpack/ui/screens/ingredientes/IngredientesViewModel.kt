package com.example.taller_jetpack.ui.screens.ingredientes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class IngredientesViewModel @Inject constructor() : ViewModel() {
    private val _ingredientes = MutableStateFlow<List<String>>(emptyList())
    val ingredientes: StateFlow<List<String>> = _ingredientes

    fun inicializar(lista: List<String>) { _ingredientes.value = lista }
    fun agregar(item: String) { _ingredientes.update { it + item } }
    fun eliminar(item: String) { _ingredientes.update { it - item } }
}