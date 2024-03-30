package org.example

import java.util.UUID

data class Libro(
    val id: UUID,
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int,
    val tematica: String,
    var estado: EstadoLibro = EstadoLibro.DISPONIBLE
) {

    init {
        requireNoVacio(titulo, "El titulo no debe estar vacio.")
        requireNoVacio(autor, "El autor no debe estar vacio")
        require(anioPublicacion.toString().length == 4) { "El año de publicacion debe ser de 4 digitos." }
        requireNoVacio(tematica, "La tematica no debe estar vacia")
    }

    private fun requireNoVacio(valor: String, mensajeError: String) {
        require(valor.isNotBlank()) { mensajeError }
    }

}