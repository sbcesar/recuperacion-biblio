package org.example

import java.util.UUID

abstract class ElementoBiblioteca(
    protected open val id: UUID,
    protected open val titulo: String,
    protected open var estado: Estado = Estado.DISPONIBLE
) {

    /**
     * Devuelve una representación en formato de cadena de texto del elemento de biblioteca.
     *
     * @return Cadena de texto representando el elemento de biblioteca.
     */
    override fun toString(): String {
        return "Elemento de Biblioteca:\n- Id: $id\n- Título: $titulo\n- Estado: ${estado.descripcion}"
    }
}