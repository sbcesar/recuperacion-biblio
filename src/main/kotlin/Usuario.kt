package org.example

import java.util.UUID

/**
 * Clase que representa a un usuario en la biblioteca.
 *
 * @property id Identificador único del usuario.
 * @property nombre Nombre del usuario.
 */
data class Usuario(
    private val id: UUID,
    private val nombre: String
) {
    init {
        require(nombre.isNotBlank()) { "El nombre de usuario no debe estar vacio." }
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return Identificador único del usuario.
     */
    fun obtenerId(): UUID {
        return id
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    fun obtenerNombre(): String{
        return nombre
    }

    /**
     * Devuelve una representación en formato de cadena de texto del usuario.
     *
     * @return Cadena de texto representando al usuario.
     */
    override fun toString(): String {
        return "Usuario:\n- Id: $id\n- Nombre: $nombre"
    }
}