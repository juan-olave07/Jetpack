package com.example.taller_jetpack.ui.screens.recetasIA

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taller_jetpack.data.model.Receta
import com.example.taller_jetpack.data.repository.IARepository
import com.example.taller_jetpack.data.repository.RecetaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

data class RecetasIAState(
    val textoStreaming: String = "",
    val recetas: List<Receta> = emptyList(),
    val cargando: Boolean = false,
    val error: String? = null,
    val guardando: Set<Int> = emptySet()
)

@HiltViewModel
class RecetasIAViewModel @Inject constructor(
    private val iaRepository: IARepository,
    private val recetaRepository: RecetaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecetasIAState())
    val state: StateFlow<RecetasIAState> = _state.asStateFlow()

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    fun generarRecetas(ingredientes: List<String>) {
        if (_state.value.cargando) return
        viewModelScope.launch {
            _state.update { it.copy(cargando = true, textoStreaming = "", recetas = emptyList(), error = null) }
            var textoAcumulado = ""

            iaRepository.generarRecetasStream(ingredientes)
                .catch { e -> _state.update { it.copy(error = e.message, cargando = false) } }
                .collect { chunk ->
                    textoAcumulado += chunk
                    _state.update { it.copy(textoStreaming = textoAcumulado) }
                }

            // Intentar parsear el JSON final
            runCatching {
                val jsonLimpio = textoAcumulado
                    .substringAfter("[")
                    .substringBeforeLast("]")
                    .let { "[$it]" }
                json.decodeFromString<List<Receta>>(jsonLimpio)
            }.onSuccess { recetas ->
                _state.update { it.copy(recetas = recetas, cargando = false) }
            }.onFailure {
                _state.update { it.copy(cargando = false) }
            }
        }
    }

    fun guardarFavorita(index: Int) {
        val receta = _state.value.recetas.getOrNull(index) ?: return
        viewModelScope.launch {
            _state.update { it.copy(guardando = it.guardando + index) }
            recetaRepository.guardarFavorita(receta)
            // Pequeña demora para feedback visual
            kotlinx.coroutines.delay(800)
            _state.update { it.copy(guardando = it.guardando - index) }
        }
    }
}