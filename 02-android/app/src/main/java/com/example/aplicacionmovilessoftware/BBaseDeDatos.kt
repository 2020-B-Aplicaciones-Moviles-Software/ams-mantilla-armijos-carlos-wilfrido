package com.example.aplicacionmovilessoftware

class BBaseDeDatos {
    companion object{
        val arregloEnteros = arrayListOf<Int>()
        val arregloEntrenadores = arrayListOf<BEntrenador>()

//        fun inicializarArreglo(){
//            arregloEnteros.add(1)
//            arregloEnteros.add(2)
//            arregloEnteros.add(3)
//            arregloEnteros.add(4)
//            arregloEnteros.add(5)
//        }

        fun inicializarArreglo(){
            arregloEntrenadores.add(BEntrenador("Pepe Pérez","Fútbol"))
            arregloEntrenadores.add(BEntrenador("Juan Ocaña", "Bascket"))
            arregloEntrenadores.add(BEntrenador("Pedro Montalvo","Voley"))
            arregloEntrenadores.add(BEntrenador("Mario Cantinflas","Indor"))
        }

        fun anadirItemAlArreglo(item: BEntrenador){
            arregloEntrenadores.add(item)
        }
    }
}