package com.example.taller_jetpack.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taller_jetpack.data.repository.TransaccionRepository
import com.example.taller_jetpack.domain.model.Transaccion
import com.example.taller_jetpack.domain.usecase.AgregarTransaccionUseCase
import com.example.taller_jetpack.domain.usecase.ObtenerResumenUseCase
import com.example.taller_jetpack.domain.usecase.Resumen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinanzasViewModel @Inject constructor(

    // Repositorio para acceder a las transacciones
    private val repo: TransaccionRepository,

    // Caso de uso encargado de validar y agregar transacciones
    private val agregarUseCase: AgregarTransaccionUseCase,

    // Caso de uso encargado de calcular el resumen financiero
    private val resumenUseCase: ObtenerResumenUseCase

) : ViewModel() {

    // Flujo observable con todas las transacciones almacenadas
    val transacciones: StateFlow<List<Transaccion>> = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _resumen = MutableStateFlow(Resumen(0.0, 0.0, 0.0))

    // Calcula automáticamente ingresos, gastos y saldo total
    val resumen: StateFlow<Resumen> = transacciones.map { lista ->
        val ingresos = lista.filter { it.tipo == "INGRESO" }.sumOf { it.monto }
        val gastos   = lista.filter { it.tipo == "GASTO"   }.sumOf { it.monto }

        Resumen(ingresos, gastos, ingresos - gastos)

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Resumen(0.0, 0.0, 0.0))

    // Flujo para enviar mensajes a la interfaz
    private val _mensaje = MutableSharedFlow<String>()
    val mensaje: SharedFlow<String> = _mensaje.asSharedFlow()

    init {

        // Actualiza el resumen cada vez que cambian las transacciones
        viewModelScope.launch {
            transacciones.collect { _resumen.value = resumenUseCase() }
        }
    }

    fun agregar(transaccion: Transaccion) {

        // Ejecuta el agregado de manera asíncrona
        viewModelScope.launch {

            when (val r = agregarUseCase(transaccion)) {

                // Mensaje cuando la transacción se guarda correctamente
                is AgregarTransaccionUseCase.Resultado.Exito ->
                    _mensaje.emit("Transacción guardada")

                // Mensaje en caso de error
                is AgregarTransaccionUseCase.Resultado.Error ->
                    _mensaje.emit(r.mensaje)
            }
        }
    }

    fun eliminar(transaccion: Transaccion) {

        // Elimina una transacción del repositorio
        viewModelScope.launch { repo.eliminar(transaccion) }
    }
}