package org.example

class GestorBiblioteca(
    val info: IGestorEntradaSalida
) {
    private val catalogo = Catalogo()
    val registroPrestamo = RegistroPrestamo()

    fun agregarLibro(libro: Libro) {
        val encontrado = catalogo.librosDisponibles.find { it == libro }

        if (encontrado == null) {
            catalogo.librosDisponibles.add(libro)
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} ha sido agregado al catálogo.")
        } else {
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} ya está en el catálogo.")
        }
    }

    fun eliminarLibro(libro: Libro) {
        val encontrado = catalogo.librosDisponibles.removeIf { it == libro }

        if (encontrado) {
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} ha sido eliminado del catálogo.")
        } else {
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} no fue encontrado en el catálogo.")
        }
    }

    fun tomarPrestado(libro: Libro) {
        if (libro.estado == EstadoLibro.PRESTADO) {
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} ya ha sido prestado.")
        } else {
            libro.estado = EstadoLibro.PRESTADO
            GestorConsola().mostrarMensaje("Has tomado prestado el libro ${libro.titulo}")
        }
    }

    fun devolverLibro(libro: Libro) {
        if (libro.estado == EstadoLibro.DISPONIBLE) {
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} ya ha sido devuelto.")
        } else {
            libro.estado = EstadoLibro.DISPONIBLE
            GestorConsola().mostrarMensaje("El libro ${libro.titulo} ha sido devuelto correctamente.")
        }
    }

    fun consultarDisponibilidad(tituloLibro: String) {
        val encontrado = catalogo.librosDisponibles.find { it.titulo == tituloLibro }

        if (encontrado != null) {
            GestorConsola().mostrarMensaje("El estado del libro $tituloLibro es ${encontrado.estado.name}")
        } else {
            GestorConsola().mostrarMensaje("El libro $tituloLibro no esta en el catalogo.")
        }
    }

    fun obtenerLibrosPorEstado(estado: EstadoLibro?): List<Libro> {
        return when (estado) {
            null -> catalogo.librosDisponibles      //Si es null se ven todos los libros
            EstadoLibro.DISPONIBLE -> catalogo.librosDisponibles.filter { it.estado == EstadoLibro.DISPONIBLE }
            EstadoLibro.PRESTADO -> catalogo.librosDisponibles.filter { it.estado == EstadoLibro.PRESTADO }
        }
    }
}