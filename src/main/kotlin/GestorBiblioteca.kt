package org.example

/**
 * Clase que gestiona las operaciones relacionadas con la biblioteca, como agregar, eliminar, prestar y devolver libros,
 * así como consultar su disponibilidad y obtener una lista de libros por estado.
 *
 * @property info Interfaz que proporciona métodos para entrada y salida de información.
 */
class GestorBiblioteca(
    private val info: IGestorEntradaSalida
) {
    private val catalogo = Catalogo()
    val registroPrestamo = RegistroPrestamo()

    /**
     * Agrega un libro al catálogo de la biblioteca.
     *
     * @param libro El libro que se desea agregar.
     */
    fun agregarLibro(libro: Libro) {
        val encontrado = catalogo.librosDisponibles.find { it.id == libro.id }

        if (encontrado == null) {
            catalogo.librosDisponibles.add(libro)
            info.mostrarMensaje("El libro ${libro.titulo} ha sido agregado al catálogo.")
        } else {
            info.mostrarMensaje("El libro ${libro.titulo} ya está en el catálogo.")
        }
    }

    /**
     * Elimina un libro del catálogo de la biblioteca.
     *
     * @param libro El libro que se desea eliminar.
     */
    fun eliminarLibro(libro: Libro) {
        val encontrado = catalogo.librosDisponibles.removeIf { it.id == libro.id }

        if (encontrado) {
            info.mostrarMensaje("El libro ${libro.titulo} ha sido eliminado del catálogo.")
        } else {
            info.mostrarMensaje("El libro ${libro.titulo} no fue encontrado en el catálogo.")
        }
    }

    /**
     * Presta un libro de la biblioteca.
     *
     * @param libro El libro que se desea prestar.
     */
    fun tomarPrestado(libro: Libro) {
        if (libro.estado == EstadoLibro.PRESTADO) {
            info.mostrarMensaje("El libro ${libro.titulo} ya ha sido prestado.")
        } else {
            libro.estado = EstadoLibro.PRESTADO
            info.mostrarMensaje("Has tomado prestado el libro ${libro.titulo}")
        }
    }

    /**
     * Devuelve un libro prestado a la biblioteca.
     *
     * @param libro El libro que se desea devolver.
     */
    fun devolverLibro(libro: Libro) {
        if (libro.estado == EstadoLibro.DISPONIBLE) {
            info.mostrarMensaje("El libro ${libro.titulo} ya ha sido devuelto.")
        } else {
            libro.estado = EstadoLibro.DISPONIBLE
            info.mostrarMensaje("El libro ${libro.titulo} ha sido devuelto correctamente.")
        }
    }

    /**
     * Consulta la disponibilidad de un libro en el catálogo de la biblioteca.
     *
     * @param tituloLibro El título del libro que se desea consultar.
     */
    fun consultarDisponibilidad(tituloLibro: String) {
        val encontrado = catalogo.librosDisponibles.find { it.titulo == tituloLibro }

        if (encontrado != null) {
            info.mostrarMensaje("El estado del libro $tituloLibro es ${encontrado.estado.descripcion}")
        } else {
            info.mostrarMensaje("El libro $tituloLibro no esta en el catalogo.")
        }
    }

    /**
     * Obtiene una lista de libros filtrados por estado.
     *
     * @param estado El estado por el cual filtrar los libros (null para obtener todos los libros).
     * @return Lista de libros filtrados por el estado especificado.
     */
    fun obtenerLibrosPorEstado(estado: EstadoLibro?): List<Libro> {
        return when (estado) {
            null -> catalogo.librosDisponibles      //Si es null se ven todos los libros
            EstadoLibro.DISPONIBLE -> catalogo.librosDisponibles.filter { it.estado == EstadoLibro.DISPONIBLE }
            EstadoLibro.PRESTADO -> catalogo.librosDisponibles.filter { it.estado == EstadoLibro.PRESTADO }
        }
    }
}