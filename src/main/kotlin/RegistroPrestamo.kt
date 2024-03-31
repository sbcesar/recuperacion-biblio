package org.example

/**
 * Clase que representa el registro de préstamos de libros en la biblioteca.
 *
 * @property info Gestor de entrada y salida para mostrar mensajes.
 * @property registroPrestamoActual Mapa mutable que mantiene el registro de los préstamos actuales, con el usuario como clave y el libro prestado como valor.
 * @property historialPrestamo Mapa mutable que mantiene un historial de todos los préstamos realizados, con el usuario como clave y una lista de cadenas que describen las acciones realizadas como valor.
 */
class RegistroPrestamo(
    private val info: IGestorEntradaSalida
) : IgestorPrestamos {
    private val registroPrestamoActual = mutableMapOf<Usuario,Libro>()              //Mapa mutable con el usuario y el libro tomado prestado
    private val historialPrestamo = mutableMapOf<Usuario,MutableList<String>>()     //Mapa mutable con el usuario y una lista de cadenas con lo que ha realizado(tomar prestado/devolver)

    /**
     * Registra un préstamo de un libro a un usuario.
     *
     * @param usuario El usuario al que se le presta el libro.
     * @param libro El libro que se presta al usuario.
     */
    override fun registrarPrestamo(usuario: Usuario,libro: Libro) {
        if (libro.obtenerEstado() == Estado.PRESTADO) {
            info.mostrarMensaje("El libro ya ha sido prestado.")
        } else {
            registroPrestamoActual[usuario] = libro

            if (!historialPrestamo.containsKey(usuario)) {
                historialPrestamo[usuario] = mutableListOf()
                historialPrestamo[usuario]?.add("El libro ${libro.obtenerTitulo()} ha sido tomado prestado por ${usuario.obtenerNombre()}")
            } else {
                historialPrestamo[usuario]?.add("El libro ${libro.obtenerTitulo()} ha sido tomado prestado por ${usuario.obtenerNombre()}")
            }
        }
    }

    /**
     * Registra la devolución de un libro por parte de un usuario.
     *
     * @param usuario El usuario que devuelve el libro.
     * @param libro El libro que se devuelve.
     */
    override fun registrarDevolucion(usuario: Usuario,libro: Libro) {
        if (!registroPrestamoActual.containsKey(usuario)) {
            info.mostrarMensaje("El usuario ${usuario.obtenerNombre()} no ha tomado prestado ningun libro o no existe.")
        } else {
            val encontrar = registroPrestamoActual[usuario]

            if (encontrar == null) {
                info.mostrarMensaje("El usuario ${usuario.obtenerNombre()} no ha tomado prestado ningun libro.")
            } else {
                registroPrestamoActual.remove(usuario,libro)
                libro.establecerEstado(Estado.DISPONIBLE)
                info.mostrarMensaje("El libro ${libro.obtenerTitulo()} ha sido devuelto.")
            }
        }
    }

    /**
     * Consulta el historial de préstamos de un libro específico.
     *
     * @param libro El libro del que se quiere consultar el historial de préstamos.
     * @return Lista de usuarios que han tomado prestado el libro.
     */
    override fun consultarHistorialLibro(libro: Libro): List<Usuario> {
        val usuariosConPrestamos = mutableListOf<Usuario>()
        registroPrestamoActual.forEach { (usuario, prestamo) ->
            if (prestamo == libro) {
                usuariosConPrestamos.add(usuario)
            }
        }
        return usuariosConPrestamos
    }

    /**
     * Consulta el historial de préstamos de un usuario específico.
     *
     * @param usuario El usuario del que se quiere consultar el historial de préstamos.
     * @return Lista de cadenas que describen las acciones realizadas por el usuario (tomar prestado/devolver libros).
     */
    override fun consultarHistorialUsuario(usuario: Usuario): List<String> {
        return historialPrestamo[usuario] ?: emptyList()
    }
}