package org.example

import java.util.UUID

/**
 * Representa un DVD en la biblioteca.
 *
 * @property id Identificador único del DVD.
 * @property titulo Título del DVD.
 * @property tematica Temática del DVD.
 * @property duracion Duración del DVD en minutos.
 */
data class DVD(
    override val id: UUID,
    override val titulo: String,
    val duracion: Int,
    val formato: String,
    override var estado: Estado = Estado.DISPONIBLE
) : ElementoBiblioteca(id, titulo, estado),Prestable {

    init {
        requireNoVacio(titulo,"El titulo no debe estar vacio.")
        require(duracion > 0){ "La duracion del dvd no debe ser inferior a 0." }
        requireNoVacio(formato,"El formato del dvd no debe estar vacio.")
    }

    /**
     * Realiza una validación para asegurarse de que el valor no esté vacío.
     *
     * @param valor El valor a verificar.
     * @param mensajeError El mensaje de error a mostrar si la validación falla.
     */
    private fun requireNoVacio(valor: String, mensajeError: String) {
        require(valor.isNotBlank()) { mensajeError }
    }

    override fun prestar() {
        if (estado == Estado.DISPONIBLE) {
            establecerEstado(Estado.PRESTADO)
            GestorConsola().mostrarMensaje("El libro '$titulo' ha sido prestado.")

        } else {
            GestorConsola().mostrarMensaje("El libro '$titulo' no está disponible para ser prestado.")
        }
    }

    override fun devolver() {
        if (estado == Estado.PRESTADO) {
            establecerEstado(Estado.DISPONIBLE)
            GestorConsola().mostrarMensaje("El libro '$titulo' ha sido devuelto.")
        } else {
            GestorConsola().mostrarMensaje("El libro '$titulo' no se puede devolver porque no está prestado.")
        }
    }

    /**
     * Devuelve el ID del dvd.
     */
    fun obtenerId(): UUID {
        return id
    }

    /**
     * Devuelve el título del dvd.
     */
    fun obtenerTitulo(): String {
        return titulo
    }

    /**
     * Devuelve el estado actual del dvd.
     */
    fun obtenerEstado(): Estado {
        return estado
    }

    /**
     * Devuelve la duracion del dvd
     */
    fun obtenerDuracion(): Int {
        return duracion
    }

    /**
     * Devuelve el forvato del dvd.
     */
    fun obtenerFormato(): String {
        return formato
    }

    /**
     * Modifica el estado actual del dvd.
     */
    private fun establecerEstado(nuevoEstado: Estado) {
        estado = nuevoEstado
    }

    /**
     * Devuelve una representación en formato de cadena de texto del DVD.
     *
     * @return Cadena de texto representando el DVD.
     */
    override fun toString(): String {
        return "DVD:\n- Id: $id\n- Título: $titulo\n- Duración: $duracion minutos\n- Formato: $formato"
    }
}

