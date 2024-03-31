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
    private val registroPrestamo = RegistroPrestamo(GestorConsola())

    /**
     * Agrega un libro al catálogo de la biblioteca.
     *
     * @param libro El libro que se desea agregar.
     */
    fun agregarLibro(libro: Libro) {
        val encontrado = catalogo.librosDisponibles.find { it.obtenerId() == libro.obtenerId() }

        if (encontrado == null) {
            catalogo.librosDisponibles.add(libro)
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ha sido agregado al catálogo.")
        } else {
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ya está en el catálogo.")
        }
    }

    /**
     * Elimina un libro del catálogo de la biblioteca.
     *
     * @param libro El libro que se desea eliminar.
     */
    fun eliminarLibro(libro: Libro) {
        val encontrado = catalogo.librosDisponibles.removeIf { it.obtenerTitulo() == libro.obtenerTitulo() }

        if (encontrado) {
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ha sido eliminado del catálogo.")
        } else {
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} no fue encontrado en el catálogo.")
        }
    }

    /**
     * Presta un libro de la biblioteca.
     *
     * @param libro El libro que se desea prestar.
     */
    fun tomarPrestado(libro: Libro) {
        if (libro.obtenerEstado() == EstadoLibro.PRESTADO) {
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ya ha sido prestado.")
        } else {
            libro.establecerEstado(EstadoLibro.PRESTADO)
            info.mostrarMensaje("Has tomado prestado el libro ${libro.obtenerTitulo()}")
        }
    }

    /**
     * Devuelve un libro prestado a la biblioteca.
     *
     * @param libro El libro que se desea devolver.
     */
    fun devolverLibro(libro: Libro) {
        if (libro.obtenerEstado() == EstadoLibro.DISPONIBLE) {
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ya ha sido devuelto.")
        } else {
            libro.establecerEstado(EstadoLibro.DISPONIBLE)
            info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ha sido devuelto correctamente.")
        }
    }

    /**
     * Consulta la disponibilidad de un libro en el catálogo de la biblioteca.
     *
     * @param tituloLibro El título del libro que se desea consultar.
     */
    fun consultarDisponibilidad(tituloLibro: String) {
        val encontrado = catalogo.librosDisponibles.find { it.obtenerTitulo() == tituloLibro }

        if (encontrado != null) {
            info.mostrarMensaje("El estado del libro $tituloLibro es ${encontrado.obtenerEstado().descripcion}")
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
            EstadoLibro.DISPONIBLE -> catalogo.librosDisponibles.filter { it.obtenerEstado() == EstadoLibro.DISPONIBLE }
            EstadoLibro.PRESTADO -> catalogo.librosDisponibles.filter { it.obtenerEstado() == EstadoLibro.PRESTADO }
        }
    }
}