import java.util.*
import kotlin.collections.ArrayList

fun main(){
    println("Hola mundo")

    // Java Int edad = 12;
    var edadProfesor = 31

    var sueldoProfesor: Double = 12.34

    //Mutables      /Inmutables
    //Mutables
    var edadCachorro: Int = 0
    edadCachorro = 1
    edadCachorro = 1
    edadCachorro = 3

    //Inmutables
    val numeroCedula = 18181818
    //numeroCedula = 12

    //tipos de variables
    val nombreProfesor: String = " Adrian Eguez"
    val sueldo: Double = 12.2
    val estadoCivil: Char = 'U'

    val fechaNacimiento: Date = Date()

    if(true){
        //Verdadera
    }else{
        //false
    }

    when(sueldo){
        12.2 -> {
            println("sueldo normal")
        }
        -12.2 -> println("sueldo negativo")
        else -> println ("Sueldo no reconocido")
    }

    val sueldoMayorAEstablecido:Int = if(sueldo > 12.2) 500 else 0
    //condicion ? bloque-true : bloque-false

    imprimirNombre("Carlos")

    calcularSueldo(1000.00)

    calcularSueldo(1000.00, 14.00)

    calcularSueldo(1000.00, 12.00, 3)


    //Named parameters

    calcularSueldo(
            calculoEspecial = 3,
            tasa = 12.00,
            sueldo = 1000.00
    )

    //Arreglos estáticos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    //no se pueden modificar (add, push, etc)

    //Arreglos dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //OPERADORES: sirven para los arreglos estáticos y dinámicos
    arregloEstatico.forEach {  }
    arregloDinamico.forEach {  }

    val respuestaForEach:Unit = arregloEstatico
            .forEach {
                valorActualIteracion ->
                println("Valor ${valorActualIteracion}")
            }
    println(respuestaForEach)

    arregloDinamico
            .forEachIndexed() { indice, valorActualIteracion ->
                println("Valor ${valorActualIteracion} Indice: ${indice}")
            }
    println(respuestaForEach)


    //MAP -> Muta el arreglo (Cambia los elementos del arreglo y nos devuelve un nuevo arreglo)
    val respuestaMap: List<Int> = arregloDinamico
            .map {  valorActualIteracion ->
                return@map valorActualIteracion * 10
            }
    println(respuestaMap)

    val respuestaMapDos: List<Int> = arregloDinamico
            .map {  it + 15 }
    println(respuestaMapDos)

    //Filter -> Filtrar el arreglo
    // 1. Devuelve una expresion  t o f
    // 2. Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
            .filter {
                valorActualIteracion ->
                val mayoresACinco: Boolean = valorActualIteracion > 5
                return@filter mayoresACinco
            }

    println(respuestaFilter)


    val respuestaAny:Boolean = arregloDinamico
            .any {
                valorActualIteracion ->
                return@any (valorActualIteracion > 5)
            }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
            .all {
                valorActualIteracion ->
                return@all valorActualIteracion > 5
            }
    println(respuestaAll) // false


    //REDUCE
    //1)Devuelve el acumulado
    //2) En que valor empieza
    //[1,2,3,4,5]
    // 0 = 0 +1
    // 1 = 1 +2
    // 3 = 3 +3
    // 6 = 6 +4
    // 10 = 10 +5
    // 15

    val respuestaReduce: Int =arregloDinamico
            .reduce {//Valor inicial igual a cero 0
                acumulado, valorActualIteracion ->
                return@reduce acumulado + valorActualIteracion
            }
    println(respuestaReduce)

    val respuestaReduceFold = arregloDinamico
            .fold(
                    100,
                    {
                        acumulado, valorActualIteracion ->
                        return@fold acumulado - valorActualIteracion
                    }
            )
    println(respuestaReduceFold)

    //arregloMutable.fold (empieza desde el principio
    //arregloMutable.foldRight (empieza desde el final
    //arregloMutable.reduce (empieza desde el final
    //arregloMutable.reduceRight (empieza desde el final


    //OPERADORES
    //forEach -> Unit (void)
    //map -> Arreglo
    //filter -> Arreglo
    //all ->Booleano
    //any -> Booleano
    //reduce -> Valor
    //fold -> Valor

    val vidaActual: Double = arregloDinamico
            .map { it * 2.3 }  //arreglo
            .filter { it > 20 } //areglo
            .fold( 100.00, {acc, i -> acc -i}) //valor
            .also{println(it)} //ejecutar código extra
    println("Valor vida actual ${vidaActual}") // 3.4

    val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null,2)
    val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma(null,null)
    println(ejemploUno.sumar())
    println(Suma.historialSumas)
    println(ejemploDos.sumar())
    println(Suma.historialSumas)
    println(ejemploTres.sumar())
    println(Suma.historialSumas)
    println(ejemploCuatro.sumar())
    println(Suma.historialSumas)



} //fin bloque main

fun  imprimirNombre(nombre: String): Int{
    println("Nombre ${nombre}") // template strings

    return 1
}

fun calcularSueldo(
        sueldo: Double,
        tasa: Double = 12.00,
        calculoEspecial: Int? = null
): Double {
    //String -> String?   / puede ser String o nulo
    if(calculoEspecial == null){
        return sueldo * (100 / tasa)
    }else{
        return sueldo * (100 / tasa) * calculoEspecial
    }
}


abstract class NumeroJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
            uno:Int,
            dos:Int
    ){
        //this.numeroUno
        numeroUno = uno
        //this.numeroDos
        numeroDos = dos

    }
}

abstract class Numeros(//Constructor primario
        protected var numeroUno: Int,
        protected var numeroDos: Int){
init {//bloque de codigo del constructor primario (opcional)
    println("Inicializar algunas cosas dentro de la clase")
}
}


class Suma(
        uno: Int,
        dos: Int
):Numeros(uno,dos){
    init{
        //this.numeroUno
        //this.numeroDos
        //X -> this.uno -> NO EXISTE
        //X -> this.dos -> NO EXISTE
    }

    constructor (
            uno: Int?,
            dos: Int
    ) : this(
            if (uno==null) 0 else uno,
            dos

    ){

    }

    constructor (
            uno: Int,
            dos: Int?
    ) : this(
            uno,
            if (dos==null) 0 else dos
    ){

    }
    constructor (
            uno: Int?,
            dos: Int?
    ) : this(
            if (uno==null) 0 else uno,
            if (dos==null) 0 else dos
    ){

    }

    public fun sumar(): Int{
        // this.numeroUno
        // this.numeroDos
        val total: Int = numeroUno + numeroDos
        Suma.agregarHistorial(total)
        return total
    }

    //SINGLETON
    companion object {// Métodos y Propiedades
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(nuevaSuma: Int){
            this.historialSumas.add(nuevaSuma)
        }
    }

}

class BaseDeDatos(){
    companion object{
        val datos = arrayListOf<Int>()
    }
}
//BaseDeDatos.datos