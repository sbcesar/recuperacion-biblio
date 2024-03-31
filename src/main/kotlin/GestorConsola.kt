package org.example

/**
 * Clase que implementa la interfaz IGestorEntradaSalida para gestionar la entrada y salida de datos por la consola.
 */
class GestorConsola : IGestorEntradaSalida {

    /**
     * Muestra un mensaje en la consola.
     *
     * @param mensaje El mensaje que se desea mostrar.
     */
    override fun mostrarMensaje(mensaje: String) {
        if (mensaje.isBlank()) {
            println("")
        } else {
            println(mensaje)
        }
    }

    /**
     * Lee y devuelve una cadena de texto desde la entrada estándar (consola).
     *
     * @return La cadena de texto leída desde la consola.
     */
    override fun leerTexto(): String {
        return readln()
    }

    /**
     * Lee y devuelve un número entero desde la entrada estándar (consola).
     *
     * @return El número entero leído desde la consola.
     */
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

    /**
     * Lee y devuelve un número decimal desde la entrada estándar (consola).
     *
     * @return El número decimal leído desde la consola.
     */
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