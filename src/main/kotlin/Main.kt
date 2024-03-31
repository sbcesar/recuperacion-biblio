package org.example

fun main() {
    //INSTANCIAS
    val gestorConsola = GestorConsola()
    val gestorBiblioteca = GestorBiblioteca(gestorConsola)

    //CREAR LIBROS
    val libro1 = Libro(UtilidadesBiblioteca.generarIdentificadorUnico(),"El señor de los anillos","J.R.R.Tolkien",1954,"Aventura")
    val libro2 = Libro(UtilidadesBiblioteca.generarIdentificadorUnico(),"One piece","Eiichiro Oda",1997,"Aventura",EstadoLibro.PRESTADO)
    val libro3 = Libro(UtilidadesBiblioteca.generarIdentificadorUnico(),"Solo Leveling","Chu-Gong",2024,"Aventura",EstadoLibro.PRESTADO)

    //AGREGAR
    gestorBiblioteca.agregarLibro(libro1)
    gestorBiblioteca.agregarLibro(libro2)
    gestorBiblioteca.agregarLibro(libro3)

    //ELIMINAR
    gestorBiblioteca.eliminarLibro(libro3)

    //TOMAR PRESTADO (1 correcta 2 erronea)
    gestorBiblioteca.tomarPrestado(libro1)
    gestorBiblioteca.tomarPrestado(libro2)

    //DEVOLVER (1 correcta 2 erronea)
    gestorBiblioteca.devolverLibro(libro2)
    gestorBiblioteca.devolverLibro(libro1)

    //MOSTRAR LIBROS DISPONIBLES
    gestorConsola.mostrarMensaje("Libros Disponibles:")
    gestorBiblioteca.obtenerLibrosPorEstado(EstadoLibro.DISPONIBLE).forEach { libro ->
        gestorConsola.mostrarMensaje("- ${libro.titulo}")
    }

    //MOSTRAR LIBROS PRESTADOS
    gestorConsola.mostrarMensaje("Libros Prestados:")
    gestorBiblioteca.obtenerLibrosPorEstado(EstadoLibro.PRESTADO).forEach { libro ->
        gestorConsola.mostrarMensaje("- ${libro.titulo}")
    }

    //MOSTRAR INFO LIBROS
    gestorConsola.mostrarMensaje("Todos los Libros:")
    gestorBiblioteca.obtenerLibrosPorEstado(null).forEach { libro ->
        gestorConsola.mostrarMensaje(libro.toString())
    }
}