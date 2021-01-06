import java.time.LocalDate

class Videojuego(
        var productora: String?,
        var nombre: String?,
        var precio: Double?,
        var fechaLanzamiento: LocalDate?,
        var enDesarrollo: Boolean
        ) {
        override fun toString(): String {
                return "$productora,$nombre,$precio,$fechaLanzamiento,$enDesarrollo"
        }
}