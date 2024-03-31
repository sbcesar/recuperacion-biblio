package org.example

import java.util.UUID
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Clase que proporciona utilidades para la gestión de la biblioteca.
 */
class UtilidadesBiblioteca {
    companion object {

        /**
         * Genera y retorna un identificador único utilizando UUID.
         *
         * @return Un identificador único generado.
         */
        fun generarIdentificadorUnico(): UUID {
            return UUID.randomUUID()
        }

        /**
         * Redondea el valor de tipo flotante al número de posiciones decimales especificado.
         *
         * @param posiciones El número de posiciones decimales a las que se desea redondear.
         * @return El valor flotante redondeado.
         */
        fun Float.redondear(posiciones: Int): Float {
            val factor = 10.0.pow(posiciones.toDouble()).toFloat()
            return (this * factor).roundToInt() / factor
        }
    }
}