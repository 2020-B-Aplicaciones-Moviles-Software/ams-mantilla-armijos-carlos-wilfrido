import CRUDProductora.Companion.listaProd
import CRUDVideojuego.Companion.listaVid
import java.lang.NumberFormatException

class Menus {
    private val listaProd = CRUDProductora.read()
    private val listaVid = CRUDVideojuego.read()
    companion object{

        fun menu() {
            println("Elija una opción: ")
            println("1 - Crear Productora\n")
            println("2 - Ver todas las Productoras\n")
            println("3 - Ver todos los Videojuegos\n")
            println("4 - Salir\n")
            try {
                when (readLine()?.toInt()) {
                    1 -> CRUDProductora.create()
                    2 -> {
                        for (i in 0 until listaProd.size){
                            println("${i+1} - "+listaProd[i].toString())
                        }
                        menuProd()
                        }
                    3 -> {
                        for (i in 0 until listaVid.size){
                            println("${i+1} - "+listaVid[i].toString())
                        }
                        menuVid()
                    }
                    4 -> System.exit(0)
                }
            } catch (ex: NumberFormatException) {
                println("¡El valor ingresado no es un número dentro del rango!")
                menu()
            }
        }

        fun menuProd(){
            println("Elija una opción: ")
            println("1 - Actualizar Productora\n")
            println("2 - Eliminar Productora\n")
            println("3 - Atrás\n")
            try {
                when (readLine()?.toInt()) {
                    1 -> menuUpdate()
                    2 -> menuDelete()
                    3 -> menu()
                }
            } catch (ex: NumberFormatException) {
                println("¡El valor ingresado no es un número dentro del rango!")
                menu()
            }
        }



        fun menuUpdate(){
            for (i in 0 until listaProd.size){
                println("${i+1} - "+listaProd[i].toString())
            }
            println("Ingrese el número de la Productora que desea cambiar: ")
            val opcion = readLine()
            if (opcion != null) {
                if (opcion.toInt()<=listaProd.size) {
                    CRUDProductora.update(opcion.toInt()-1)
                }else{
                    println("El valor ingresado excede la cantidad de la lista!")
                    println("¿Si desea intentarlo de nuevo precione 1?")
                    if (readLine()=="1") {
                        menuUpdate()
                    }else{
                        menu()
                    }
                }
            }
        }

        fun menuDelete(){
            for (i in 0 until listaProd.size){
                println("${i+1} - "+listaProd[i].archivo())
            }
            println("\nIngrese el indice de la Productora que desea eliminar: ")
            val opcion= readLine()
            if (opcion != null) {
                if (opcion.toInt()<=listaProd.size) {
                    println("Si está seguro que desea eliminar ese valor presione 1")
                    if (readLine()=="1") {
                        CRUDProductora.delete(opcion.toInt()-1)
                    }else{
                        menuDelete()
                    }
                }else{
                    println("El valor ingresado excede la cantidad de Productoras!")
                    println("¿Si desea intentarlo de nuevo precione 1?")
                    if (readLine()=="1") {
                        menuDelete()
                    }else{
                        menu()
                    }
                }
            }
        }

        //VIDEOJUEGOS
        fun menuVid(){
            println("Elija una opción: ")
            println("1 - Crear Videojuego\n")
            println("2 - Actualizar Videojuego\n")
            println("3 - Eliminar Videojuego\n")
            println("4 - Atrás\n")
            try {
                when (readLine()?.toInt()) {
                    1 ->CRUDVideojuego.create()
                    2 -> menuUpdateVid()
                    3 -> menuDeleteVid()
                    4 -> menu()
                }
            } catch (ex: NumberFormatException) {
                println("¡El valor ingresado no es un número dentro del rango!")
                menu()
            }
        }
        fun menuUpdateVid(){
            for (i in 0 until listaVid.size){
                println("${i+1} - "+listaVid[i].toString())
            }
            println("Ingrese el número del Videojuego que desea cambiar: ")
            val opcion = readLine()
            if (opcion != null) {
                if (opcion.toInt()<=listaVid.size) {
                    CRUDVideojuego.update(opcion.toInt()-1)
                }else{
                    println("El valor ingresado excede la cantidad de la lista!")
                    println("¿Si desea intentarlo de nuevo precione 1?")
                    if (readLine()=="1") {
                        menuUpdateVid()
                    }else{
                        menu()
                    }
                }
            }
        }

        fun menuDeleteVid(){
            for (i in 0 until listaVid.size){
                println("${i+1} - "+listaVid[i].toString())
            }
            println("\nIngrese el indice del Videojuego que desea eliminar: ")
            val opcion= readLine()
            if (opcion != null) {
                if (opcion.toInt()<=listaVid.size) {
                    println("Si está seguro que desea eliminar ese valor presione 1")
                    if (readLine()=="1") {
                        CRUDVideojuego.delete(opcion.toInt()-1)
                    }else{
                        menuDeleteVid()
                    }
                }else{
                    println("El valor ingresado excede la cantidad de la lista!")
                    println("Si desea intentarlo de nuevo precione 1")
                    if (readLine()=="1") {
                        menuDeleteVid()
                    }else{
                        menu()
                    }
                }
            }
        }
    }

}