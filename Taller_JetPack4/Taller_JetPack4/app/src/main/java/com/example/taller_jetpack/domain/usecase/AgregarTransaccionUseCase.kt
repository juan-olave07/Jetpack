package com.example.taller_jetpack.domain.usecase

import com.example.taller_jetpack.data.repository.TransaccionRepository
import com.example.taller_jetpack.domain.model.Transaccion
import javax.inject.Inject

// Caso de uso encargado de validar y guardar las transacciones
class AgregarTransaccionUseCase @Inject constructor(

    // Repositorio utilizado para insertar datos
    private val repository: TransaccionRepository

) {

    // Define los posibles resultados de la operación
    sealed class Resultado {

        // Resultado exitoso
        data object Exito : Resultado()

        // Resultado con mensaje de error
        data class Error(val mensaje: String) : Resultado()
    }

    // Función principal que valida y registra una transacción
    suspend operator fun invoke(transaccion: Transaccion): Resultado {

        // Verifica que la descripción no esté vacía
        if (transaccion.descripcion.isBlank())
            return Resultado.Error("La descripción no puede estar vacía")

        // Verifica que el monto sea mayor a cero
        if (transaccion.monto <= 0)
            return Resultado.Error("El monto debe ser mayor a cero")

        // Verifica que exista una categoría
        if (transaccion.categoria.isBlank())
            return Resultado.Error("Selecciona una categoría")

        // Verifica que el tipo sea válido
        if (transaccion.tipo !in listOf("INGRESO", "GASTO"))
            return Resultado.Error("Tipo inválido")

        // Guarda la transacción en el repositorio
        repository.insertar(transaccion)

        // Retorna éxito si todo salió correctamente
        return Resultado.Exito
    }
}