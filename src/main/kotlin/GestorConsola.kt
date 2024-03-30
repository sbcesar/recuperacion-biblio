package org.example

class GestorConsola : IGestorEntradaSalida {

    override fun mostrarMensaje(mensaje: String) {
        if (mensaje.isBlank()) {
            println("")
        } else {
            println(mensaje)
        }
    }

    override fun leerTexto(): String {
        return readln()
    }

    override fun leerEntero(): Int {
        var entrada: Int? = null
        while (entrada == null) {
            try {
                entrada = readln().toInt()
            } catch (e: NumberFormatException) {
                mostrarMensaje("*** ERROR *** Numero entero no valido.")
            }
        }
        return entrada
    }

    override fun leerDecimal(): Double {
        var entrada: Double? = null
        while (entrada == null) {
            try {
                entrada = readln().toDouble()
            } catch (e: NumberFormatException) {
                mostrarMensaje("*** ERROR *** Numero decimal no valido.")
            }
        }
        return entrada
    }
}