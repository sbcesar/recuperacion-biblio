package org.example

/**
 * Interfaz que define métodos para la gestión de entrada y salida de información.
 */
interface IGestorEntradaSalida {

    /**
     * Muestra un mensaje en la salida.
     *
     * @param mensaje El mensaje que se desea mostrar.
     */
    fun mostrarMensaje(mensaje: String)

    /**
     * Lee y devuelve una cadena de texto desde la entrada.
     *
     * @return La cadena de texto leída desde la entrada.
     */
    fun leerTexto(): String

    /**
     * Lee y devuelve un número entero desde la entrada.
     *
     * @return El número entero leído desde la entrada.
     */
    fun leerEntero(): Int

    /**
     * Lee y devuelve un número decimal desde la entrada.
     *
     * @return El número decimal leído desde la entrada.
     */
    fun leerDecimal(): Double
}