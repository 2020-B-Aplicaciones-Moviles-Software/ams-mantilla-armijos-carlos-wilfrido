import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CRUDVideojuego {
    companion object{
        val url = "src/main/resources/Productoras"
        val file = File(url)
        var temp = arrayListOf<String?>()
        val listaVid: MutableList<Videojuego> = read()

        fun create(){
            var productora: String?
            var nombre: String?
            var precio: Double?
            var fechaLanzamiento: String?
            var enDesarrollo: Boolean
            do {
                println("Ingrese el nombre de la productora del videojuego: ")
                productora = readLine()
                println("Ingrese el nombre del videojuego: ")
                nombre = readLine()
                println("Ingrese el precio del videojuego (si lo tiene)")
                precio = readLine()?.toDouble()
                println("Ingrese la fecha de lanzamiento del  videojuego(1990-12-31): ")
                fechaLanzamiento = readLine()
                println("Ingrese 'true' si el juego se encuetra en desarrollo")
                enDesarrollo = readLine().equals("true")
            }while (productora.isNullOrEmpty()||nombre.isNullOrEmpty()||fechaLanzamiento.isNullOrEmpty())

            var videojuego = Videojuego(productora,nombre, precio, LocalDate.parse(fechaLanzamiento, DateTimeFormatter.ISO_DATE), enDesarrollo)
            Archivos.escribirVideojuego(videojuego)
        }

        fun read (productora: String): MutableList<Videojuego> {
            val arregloVideojuego = arrayListOf<Videojuego>()
            val videojuegos = Archivos.leerVideojuegos()
            for (i in 0 until videojuegos.size){
                temp = videojuegos[i].split(",") as ArrayList<String?>
                if (temp[0]==productora) {
                    if (temp[2].equals("null")){
                        arregloVideojuego.add(Videojuego(temp[0], temp[1], null, LocalDate.parse(temp[3], DateTimeFormatter.ISO_DATE), temp[4].toBoolean()))
                    }else{
                        arregloVideojuego.add(Videojuego(temp[0], temp[1], temp[2]?.toDouble(), LocalDate.parse(temp[3], DateTimeFormatter.ISO_DATE), temp[4].toBoolean()))
                }
                }
            }

            return arregloVideojuego
        }

        fun read (): MutableList<Videojuego> {
            val arregloVideojuego = arrayListOf<Videojuego>()
            val videojuegos = Archivos.leerVideojuegos()
            for (i in 0 until videojuegos.size){
                temp = videojuegos[i].split(",") as ArrayList<String?>

                    if (temp[2].equals("null")){
                        arregloVideojuego.add(Videojuego(temp[0], temp[1], null, LocalDate.parse(temp[3], DateTimeFormatter.ISO_DATE), temp[4].toBoolean()))
                    }else{
                        arregloVideojuego.add(Videojuego(temp[0], temp[1], temp[2]?.toDouble(), LocalDate.parse(temp[3], DateTimeFormatter.ISO_DATE), temp[4].toBoolean()))
                    }

            }

            return arregloVideojuego
        }

        fun update(indice: Int){
            var productora: String?
            var nombre: String?
            var precio: String?
            var fechaLanzamiento: String?
            var enDesarrollo: String?
            val lista: MutableList<Videojuego> = read()
            var videojuego = lista[indice]

            println("Ingrese el nombre de la productora del videojuego: ")
            productora = readLine()
            println("Ingrese el nombre del videojuego: ")
            nombre = readLine()
            println("Ingrese el precio del videojuego (si lo tiene)")
            precio = readLine()
            println("Ingrese la fecha de lanzamiento del  videojuego(1990-12-31): ")
            fechaLanzamiento = readLine()
            println("Ingrese 'true' si el juego se encuetra en desarrollo")
            enDesarrollo = readLine()

            if (productora.isNullOrEmpty())
                productora = videojuego.productora
            if (nombre.isNullOrEmpty())
                nombre = videojuego.nombre
            if (precio.isNullOrEmpty())
                precio = videojuego.precio.toString()
            if (fechaLanzamiento.isNullOrEmpty())
                fechaLanzamiento = videojuego.fechaLanzamiento.toString()
            if (enDesarrollo.isNullOrEmpty())
                enDesarrollo = videojuego.enDesarrollo.toString()
            else if(!enDesarrollo.equals("true"))
                enDesarrollo = "false"

            lista.set(indice,Videojuego(productora,nombre, precio.toDouble(), LocalDate.parse(fechaLanzamiento, DateTimeFormatter.ISO_DATE), enDesarrollo.toBoolean()))
            Archivos.escribirVideojuegoLista(lista)
        }

        fun delete(indice: Int){
            val lista: MutableList<Videojuego> = read()
            lista.remove(lista[indice])
            Archivos.escribirVideojuegoLista(lista)
        }

        fun delete(productora: String){
            val videojuegos: MutableList<Videojuego> = read()
            var i = 0
            while (i < videojuegos.size){
                if (videojuegos[i].productora.equals(productora)){
                    videojuegos.removeAt(i)
                    i--
                }
                i++
            }
            Archivos.escribirVideojuegoLista(videojuegos)
        }

    }
}