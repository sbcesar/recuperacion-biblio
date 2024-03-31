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
    val id: UUID,
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int,
    val tematica: String,
    var estado: EstadoLibro = EstadoLibro.DISPONIBLE
) {

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

    /**
     * Devuelve una representación en formato de cadena de texto del libro.
     *
     * @return Cadena de texto representando el libro.
     */
    override fun toString(): String {
        return "Libro:\n- Id: $id\n- Titulo: $titulo\n- Autor: $autor\n- Año de publicacion: $anioPublicacion\n- Tematica: $tematica\n- Estado: ${estado.descripcion}"
    }
}