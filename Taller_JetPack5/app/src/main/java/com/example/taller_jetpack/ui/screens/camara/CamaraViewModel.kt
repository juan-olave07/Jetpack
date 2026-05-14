package com.example.taller_jetpack.ui.screens.camara

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CamaraViewModel @Inject constructor() : ViewModel() {
    private val _analizando = MutableStateFlow(false)
    val analizando: StateFlow<Boolean> = _analizando

    fun setAnalizando(valor: Boolean) { _analizando.value = valor }
}