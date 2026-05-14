package com.example.taller_jetpack.domain.usecase

import com.example.taller_jetpack.data.repository.TransaccionRepository
import jakarta.inject.Inject

data class Resumen(
    val totalIngresos: Double,
    val totalGastos: Double,
    val balance: Double
)

class ObtenerResumenUseCase @Inject constructor(
    private val repository: TransaccionRepository
) {
    suspend operator fun invoke(): Resumen {
        val ingresos = repository.getTotalPorTipo("INGRESO")
        val gastos = repository.getTotalPorTipo("GASTO")
        return Resumen(
            totalIngresos = ingresos,
            totalGastos = gastos,
            balance = ingresos - gastos
        )
    }
}