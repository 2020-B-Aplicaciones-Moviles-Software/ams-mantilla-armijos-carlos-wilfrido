import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CRUDProductora {
    companion object{
        var arregloProductora = arrayListOf<Productora>()
        val url = "src/main/resources/Productoras"
        val file = File(url)
        var temp = arrayListOf<String>()
        val listaProd: MutableList<Productora> = read()

        fun create(){
            var nombre: String?
            var pagina: String?
            var fechaFundacion: String?
            var empleados: Int?
            do {
                println("Ingrese el nombre: ")
                nombre = readLine()
                println("Ingrese la página: ")
                pagina = readLine()
                println("Ingrese la fecha de fundación(1990-12-31): ")
                fechaFundacion = readLine()
                println("Ingrese la cantidad de empleados: ")
                empleados = readLine()?.toInt()
            }while (nombre.isNullOrEmpty()||pagina.isNullOrEmpty()||fechaFundacion.isNullOrEmpty())

            var productora = Productora(nombre,pagina, LocalDate.parse(fechaFundacion, DateTimeFormatter.ISO_DATE), empleados,null)
            Archivos.escribirProductora(productora)
        }

        fun read (): MutableList<Productora> {

            val productoras = Archivos.leerProductoras()
            println(productoras.size)
            for (i in 0 until productoras.size){
                temp = productoras[i].split(",") as ArrayList<String>
                arregloProductora
                        .add(
                                Productora(temp[0],
                                        temp[1],
                                        LocalDate.parse(temp[2], DateTimeFormatter.ISO_DATE),
                                        temp[3].toInt(),
                                        CRUDVideojuego.read(temp[0]))
                        )
            }

            return arregloProductora
        }

        fun update(indice: Int){
            var nombre: String?
            var pagina: String?
            var fechaFundacion: String?
            var empleados: String?

            val productora = listaProd[indice]

            println("Ingrese el nombre: ")
            nombre = readLine()
            println("Ingrese la página: ")
            pagina = readLine()
            println("Ingrese la fecha de fundación(1990-12-31): ")
            fechaFundacion = readLine()
            println("Ingrese la cantidad de empleados: ")
            empleados = readLine()

            if (nombre.isNullOrEmpty())
                nombre=productora.nombre
            if (pagina.isNullOrEmpty())
                pagina=productora.pagina
            if (fechaFundacion.isNullOrEmpty())
                fechaFundacion=productora.fundacion.toString()
            if (empleados.isNullOrEmpty())
                empleados=productora.numeroEmpleados.toString()

            listaProd.set(indice,Productora(nombre,pagina, LocalDate.parse(fechaFundacion, DateTimeFormatter.ISO_DATE),empleados?.toInt(),productora.videojuegos))
            Archivos.escribirProductoraLista(listaProd)
        }

        fun delete(indice: Int){

            CRUDVideojuego.delete(listaProd[indice].nombre)
            listaProd.remove(listaProd[indice])
            Archivos.escribirProductoraLista(listaProd)
        }
    }
}