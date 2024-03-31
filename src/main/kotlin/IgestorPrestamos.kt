package org.example

interface IgestorPrestamos {
    fun registrarPrestamo(usuario: Usuario,libro: Libro)

    fun registrarDevolucion(usuario: Usuario,libro: Libro)

    fun consultarHistorialLibro(libro: Libro): List<Usuario>

    fun consultarHistorialUsuario(usuario: Usuario): List<String>
}