package org.example

fun main() {
    //INSTANCIAS
    val gestorConsola = GestorConsola()
    val registroPrestamo = RegistroPrestamo(gestorConsola)
    val gestorBiblioteca = GestorBiblioteca(gestorConsola,registroPrestamo)

    //CREAR LIBROS
    val libro1 = Libro(UtilidadesBiblioteca.generarIdentificadorUnico(),"El señor de los anillos","J.R.R.Tolkien",1954,"Aventura")
    val libro2 = Libro(UtilidadesBiblioteca.generarIdentificadorUnico(),"One piece","Eiichiro Oda",1997,"Aventura",Estado.PRESTADO)
    val libro3 = Libro(UtilidadesBiblioteca.generarIdentificadorUnico(),"Solo Leveling","Chu-Gong",2024,"Aventura",Estado.PRESTADO)

    //CREAR USUARIOS
    val usuario1 = Usuario(UtilidadesUsuario.generarIdentificadorUnico(),"Cesar")
    val usuario2 = Usuario(UtilidadesUsuario.generarIdentificadorUnico(),"Pedro")
    val usuario3 = Usuario(UtilidadesUsuario.generarIdentificadorUnico(),"Diego")

    //AGREGAR LIBRO
    gestorBiblioteca.agregarLibro(libro1)
    gestorBiblioteca.agregarLibro(libro2)
    gestorBiblioteca.agregarLibro(libro3)


    //ELIMINAR
    gestorBiblioteca.eliminarLibro(libro3)

    //TOMAR PRESTADO (1 correcta 2 erronea)
    gestorBiblioteca.tomarPrestado(usuario1,libro1)
    gestorBiblioteca.tomarPrestado(usuario2,libro2)


    //DEVOLVER (1 correcta 2 erronea)
    gestorBiblioteca.devolverLibro(usuario2,libro2)
    gestorBiblioteca.devolverLibro(usuario1,libro1)

    //------------------
    libro1.prestar()
    libro2.prestar()

    libro2.devolver()
    libro1.devolver()
    //------------------

    //MOSTRAR LIBROS DISPONIBLES
    gestorConsola.mostrarMensaje("\nLibros Disponibles:")
    gestorBiblioteca.obtenerLibrosPorEstado(Estado.DISPONIBLE).forEach { libro ->
        gestorConsola.mostrarMensaje("- ${libro.obtenerTitulo()}")
    }

    //MOSTRAR LIBROS PRESTADOS
    gestorConsola.mostrarMensaje("\nLibros Prestados:")
    gestorBiblioteca.obtenerLibrosPorEstado(Estado.PRESTADO).forEach { libro ->
        gestorConsola.mostrarMensaje("- ${libro.obtenerTitulo()}")
    }

    //MOSTRAR INFO LIBROS
    gestorConsola.mostrarMensaje("\nTodos los Libros:")
    gestorBiblioteca.obtenerLibrosPorEstado(null).forEach { libro ->
        gestorConsola.mostrarMensaje(libro.toString())
    }

    println()

    //REGISTRAR PRESTAMO
    registroPrestamo.registrarPrestamo(usuario1,libro1)     //esta disponible - no da error
    registroPrestamo.registrarPrestamo(usuario1,libro2)     //esta prestado - da error

    //REGISTRAR DEVOLUCION
    registroPrestamo.registrarDevolucion(usuario1,libro1)   //no da error
    registroPrestamo.registrarDevolucion(usuario3,libro3)   //da error

    //CONSULTAR HISTORIAL POR LIBRO
    gestorConsola.mostrarMensaje("\nHistorial del libro ${libro1.obtenerTitulo()}:")
    registroPrestamo.consultarHistorialLibro(libro1).forEach { usuario ->
        gestorConsola.mostrarMensaje("- ${usuario.obtenerNombre()}")
    }

    //CONSULTAR HISTORIAL POR USUARIO
    gestorConsola.mostrarMensaje("\nHistorial del usuario ${usuario1.obtenerNombre()}:")
    registroPrestamo.consultarHistorialUsuario(usuario1).forEach { prestamos ->
        gestorConsola.mostrarMensaje("- $prestamos")
    }
}