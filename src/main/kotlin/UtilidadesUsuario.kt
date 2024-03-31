package org.example

import java.util.*

/**
 * Clase de utilidades para operaciones relacionadas con usuarios.
 */
class UtilidadesUsuario {
    companion object {
        /**
         * Genera un identificador único (UUID) para un usuario.
         *
         * @return Identificador único generado.
         */
        fun generarIdentificadorUnico(): UUID {
            return UUID.randomUUID()
        }
    }
}
