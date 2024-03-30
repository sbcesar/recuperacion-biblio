package org.example

interface IGestorEntradaSalida {
    fun mostrarMensaje(mensaje: String)

    fun leerTexto(): String

    fun leerEntero(): Int

    fun leerDecimal(): Double
}