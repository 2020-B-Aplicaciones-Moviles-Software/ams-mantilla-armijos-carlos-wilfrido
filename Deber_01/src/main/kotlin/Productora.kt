import java.io.Serializable
import java.time.LocalDate

class Productora(
        var nombre: String,
        var pagina: String,
        var fundacion: LocalDate,
        var numeroEmpleados: Int?,
        var videojuegos: List<Videojuego>?
): Serializable {

        companion object {
        private const val serialVersionUID: Long = 123
    }

    override fun toString(): String {
        return "$nombre,$pagina,$fundacion,$numeroEmpleados,$videojuegos"
    }

    fun archivo(): String {
        return "$nombre,$pagina,$fundacion,$numeroEmpleados"
    }

}