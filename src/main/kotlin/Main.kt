package org.example

import java.util.*

fun main() {
    val gestorConsola = GestorConsola()
    val gestorBiblioteca = GestorBiblioteca(gestorConsola)

    val libro1 = Libro(UUID.randomUUID(),"El señor de los anillos","J.R.R.Tolkien",1954,"Aventura")
    val libro2 = Libro(UUID.randomUUID(),"One piece","Eiichiro Oda",1997,"Aventura",EstadoLibro.PRESTADO)
    val libro3 = Libro(UUID.randomUUID(),"Solo Leveling","Chu-Gong",2024,"Aventura",EstadoLibro.PRESTADO)

    gestorBiblioteca.agregarLibro(libro1)
    gestorBiblioteca.agregarLibro(libro2)
    gestorBiblioteca.agregarLibro(libro3)

    gestorBiblioteca.eliminarLibro(libro3)

    gestorBiblioteca.tomarPrestado(libro1)
    gestorBiblioteca.tomarPrestado(libro2)

    gestorBiblioteca.devolverLibro(libro2)
    gestorBiblioteca.devolverLibro(libro1)

    gestorConsola.mostrarMensaje("Libros Disponibles:")
    gestorBiblioteca.obtenerLibrosPorEstado(EstadoLibro.DISPONIBLE).forEach { libro ->
        gestorConsola.mostrarMensaje("- ${libro.titulo}")
    }

    gestorConsola.mostrarMensaje("Libros Prestados:")
    gestorBiblioteca.obtenerLibrosPorEstado(EstadoLibro.PRESTADO).forEach { libro ->
        gestorConsola.mostrarMensaje("- ${libro.titulo}")
    }
}