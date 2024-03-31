package org.example

import java.util.UUID

/**
 * Representa un libro en la biblioteca.
 *
 * @property id Identificador único del libro.
 * @property titulo Título del libro.
 * @property autor Autor del libro.
 * @property anioPublicacion Año de publicación del libro.
 * @property tematica Temática del libro.
 * @property estado Estado actual del libro (por defecto DISPONIBLE).
 */
data class Libro(
    override val id: UUID,
    override val titulo: String,
    private val autor: String,
    private val anioPublicacion: Int,
    private val tematica: String,
    override var estado: Estado = Estado.DISPONIBLE
): ElementoBiblioteca(id, titulo, estado),Prestable {

    /**
     * Inicializa la instancia de Libro y realiza validaciones sobre los datos proporcionados.
     */
    init {
        requireNoVacio(titulo, "El titulo no debe estar vacio.")
        requireNoVacio(autor, "El autor no debe estar vacio")
        require(anioPublicacion.toString().length == 4) { "El año de publicacion debe ser de 4 digitos." }
        requireNoVacio(tematica, "La tematica no debe estar vacia")
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
     * Devuelve el ID del libro.
     */
    fun obtenerId(): UUID {
        return id
    }

    /**
     * Devuelve el título del libro.
     */
    fun obtenerTitulo(): String {
        return titulo
    }

    /**
     * Devuelve el autor del libro.
     */
    fun obtenerAutor(): String {
        return autor
    }

    /**
     * Devuelve el año de publicación del libro.
     */
    fun obtenerAnioPublicacion(): Int {
        return anioPublicacion
    }

    /**
     * Devuelve la temática del libro.
     */
    fun obtenerTematica(): String {
        return tematica
    }

    /**
     * Devuelve el estado actual del libro.
     */
    fun obtenerEstado(): Estado {
        return estado
    }

    /**
     * Modifica el estado actual del libro.
     */
    fun establecerEstado(nuevoEstado: Estado) {
        estado = nuevoEstado
    }

    /**
     * Devuelve una representación en formato de cadena de texto del libro.
     *
     * @return Cadena de texto representando el libro.
     */
    override fun toString(): String {
        return "Libro:\n- Id: $id\n- Titulo: $titulo\n- Autor: $autor\n- Año de publicacion: $anioPublicacion\n- Tematica: $tematica\n- Estado: ${estado.descripcion}"
    }
}