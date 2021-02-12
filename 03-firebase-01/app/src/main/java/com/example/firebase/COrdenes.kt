package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebase.dto.FirestoreRestauranteDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.util.*
import kotlin.collections.ArrayList

class COrdenes : AppCompatActivity() {

    val arregloRestaurantes = arrayListOf<FirestoreRestauranteDto>()
    var adaptadorRestaurantes: ArrayAdapter<FirestoreRestauranteDto>? = null
    var restauranteSeleccionado: FirestoreRestauranteDto? = null

    val arregloTiposComida = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_ordenes)

        if (adaptadorRestaurantes == null){
            adaptadorRestaurantes = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            arregloRestaurantes
            )
            adaptadorRestaurantes?.
                    setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item
                    )
            cargarRestaurantes()
        }

        val textViewTipoComida = findViewById<TextView>(R.id.tv_tipos_comida)
        textViewTipoComida.setText("")

        val botonAnadirTipoComida = findViewById<Button>(R.id.btn_anadir_tipo_comida)
        botonAnadirTipoComida
            .setOnClickListener {
                agregarTipoComida()
            }

        val botonCrearOrden = findViewById<Button>(R.id.btn_crear_orden)
        botonCrearOrden
            .setOnClickListener {
                crearOrden()
            }

        buscarOrdenes()
        //taller()
        //consultaTaller()

        //eliminacion()
        leer()
        val etEliminarId = findViewById<EditText>(R.id.et_eliminar_id)

        val botonEliminar = findViewById<Button>(R.id.btn_eliminar)
        botonEliminar
                .setOnClickListener {
                    var delId = etEliminarId.getText().toString()
                    eliminarDocumentoMedianteConsulta(delId)
                }


    }



//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereEqualTo("review",3)
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }

    /**
     *
      */
//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereEqualTo("review",5) //buscar por 2 campos
//                .whereEqualTo("restaurante.nombre","KFC")
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }

    /**
     *
     */
//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereEqualTo("restaurante.nombre","KFC") //buscar por 2 campos
//                .whereArrayContains("tiposComida","Papas")
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }
    /**
     *
     */
//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereEqualTo("restaurante.nombre","KFC") //buscar por 2 campos
//                .whereGreaterThanOrEqualTo("review",3) //Agregar la busqueda por email del usuario
//                .whereEqualTo("usuario.email","a@a.com")
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }

    /**
     *
     */
//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereEqualTo("restaurante.nombre","KFC") //buscar por 2 campos
//                .whereGreaterThanOrEqualTo("review",3)
//                .orderBy("review", Query.Direction.DESCENDING)
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }

    /**
     *
     */
//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereEqualTo("restaurante.nombre","KFC") //buscar por 2 campos
//                .whereArrayContainsAny("tiposComida", arrayListOf("Papas","Coca cola"))
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }

    fun taller(){
        val db = Firebase.firestore
        val cities = db.collection("cities")
        val data1 = hashMapOf("name" to "San Francisco","state" to "CA","country" to "USA","capital" to false,"population" to 860000,"regions" to listOf("west_coast", "norcal"))
        cities.document("SF").set(data1)
        val data2 = hashMapOf("name" to "Los Angeles","state" to "CA","country" to "USA","capital" to false,"population" to 3900000,"regions" to listOf("west_coast", "socal"))
        cities.document("LA").set(data2)
        val data3 = hashMapOf("name" to "Washington D.C.","state" to null,"country" to "USA","capital" to true,"population" to 680000,"regions" to listOf("east_coast"))
        cities.document("DC").set(data3)
        val data4 = hashMapOf("name" to "Tokyo","state" to null,"country" to "Japan","capital" to true,"population" to 9000000,"regions" to listOf("kanto", "honshu"))
        cities.document("TOK").set(data4)
        val data5 = hashMapOf("name" to "Beijing","state" to null,"country" to "China","capital" to true,"population" to 21500000,"regions" to listOf("jingjinji", "hebei"))
        cities.document("BJ").set(data5)

        val ggbData = mapOf(
                "name" to "Golden Gate Bridge",
                "type" to "bridge"
        )
        cities.document("SF").collection("landmarks").add(ggbData)

        val lohData = mapOf(
                "name" to "Legion of Honor",
                "type" to "museum"
        )
        cities.document("SF").collection("landmarks").add(lohData)

        val gpData = mapOf(
                "name" to "Griffth Park",
                "type" to "park"
        )
        cities.document("LA").collection("landmarks").add(gpData)

        val tgData = mapOf(
                "name" to "The Getty",
                "type" to "museum"
        )
        cities.document("LA").collection("landmarks").add(tgData)

        val lmData = mapOf(
                "name" to "Lincoln Memorial",
                "type" to "memorial"
        )
        cities.document("DC").collection("landmarks").add(lmData)

        val nasaData = mapOf(
                "name" to "National Air and Space Museum",
                "type" to "museum"
        )
        cities.document("DC").collection("landmarks").add(nasaData)

        val upData = mapOf(
                "name" to "Ueno Park",
                "type" to "park"
        )
        cities.document("TOK").collection("landmarks").add(upData)

        val nmData = mapOf(
                "name" to "National Musuem of Nature and Science",
                "type" to "museum"
        )
        cities.document("TOK").collection("landmarks").add(nmData)

        val jpData = mapOf(
                "name" to "Jingshan Park",
                "type" to "park"
        )
        cities.document("BJ").collection("landmarks").add(jpData)

        val baoData = mapOf(
                "name" to "Beijing Ancient Observatory",
                "type" to "musuem"
        )
        cities.document("BJ").collection("landmarks").add(baoData)

    }

    fun consultaTaller(){
        val db = Firebase.firestore
        val referencia = db.collection("cities")
        val groupRef = db.collectionGroup("landmarks")

        referencia
                .document("BJ")
                .collection("landmarks")
                .whereEqualTo("type","park")
                .get()
                .addOnSuccessListener {
                    for (cities in it){
                        Log.i("firebase-taller","${cities.id} ${cities.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-taller","Error ${it}")
                }

        groupRef
                .whereEqualTo("type","park")
                .get()
                .addOnSuccessListener {
                    for (landmarks in it){
                        Log.i("firebase-taller","${landmarks.id} ${landmarks.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firebase","Error ${it}")
                }
    }

//    fun buscarOrdenes(){
//        val db = Firebase.firestore
//        val referencia = db.collection("orden")
//        //Buscar por un solo campo
//        referencia
//                .whereIn("restaurante.nombre", arrayListOf("KFC","McDonalds")) //buscar por 2 campos
//                .whereGreaterThanOrEqualTo("review",2)
//                .get()
//                .addOnSuccessListener {
//                    for (orden in it){
//                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
//                    }
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-firestore","Error")
//                }
//    }

    fun buscarOrdenes(){
        val db = Firebase.firestore
        val referencia = db.collection("orden")

        referencia
                .limit(2)
                .get()
                .addOnSuccessListener {
                    for (orden in it){
                        Log.i("firebase-consultas","${orden.id} ${orden.data}")
                    }
                    val ultimoRegistro: QueryDocumentSnapshot = it.last()

                    referencia
                            .limit(2)
                            .startAfter(ultimoRegistro)
                            .get()
                            .addOnSuccessListener {
                                for (orden in it){
                                    Log.i("firebase-consultas","${orden.id} ${orden.data}")
                                }
                            }
                            .addOnFailureListener {
                                Log.i("firebase-consultas","Error ${it}")
                            }
                }
                .addOnFailureListener {
                    Log.i("firebase-consultas","Error ${it}")
                }

    }

    fun cargarRestaurantes(){

        val spinnerRestaurantes = findViewById<Spinner>(R.id.sp_restaurantes)

        spinnerRestaurantes.adapter = adaptadorRestaurantes

        spinnerRestaurantes
            .onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("firebase-firestore","No seleccionó nada")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                restauranteSeleccionado = arregloRestaurantes[position]
            }
        }

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")
        referencia.get()
            .addOnSuccessListener {
                for(restaurante in it){
                    val restauranteCasteado = restaurante.toObject(
                        FirestoreRestauranteDto::class.java
                    )
                    restauranteCasteado.uid = restaurante.id
                    arregloRestaurantes.add(restauranteCasteado)
                }
                adaptadorRestaurantes?.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Log.i("firebase-firestore","Error ${it}")
            }
    }

    fun agregarTipoComida(){
        val etTipoComida = findViewById<EditText>(R.id.et_tipo_comida)
        val textoTipoComida = etTipoComida.text.toString()

        arregloTiposComida.add(textoTipoComida)

        val textViewTipoComida = findViewById<TextView>(R.id.tv_tipos_comida)

        val textoAnterior = textViewTipoComida.text.toString()

        textViewTipoComida.setText("${textoAnterior}, ${textoTipoComida}")
        etTipoComida.setText("")
    }

    fun crearOrden(){
        if (restauranteSeleccionado != null && FirebaseAuth.getInstance().currentUser != null){

            val restaurante = restauranteSeleccionado
            val usuario = BAuthUsuario.usuario
            val editTextReview = findViewById<EditText>(R.id.et_review)

            val nuevaOrden = hashMapOf<String, Any?>(
                "restaurante" to restaurante,
                "usuario" to usuario,
                "review" to editTextReview.text.toString().toInt(),
                "tiposComida" to arregloTiposComida,
                "fechaCreacion" to Timestamp(Date())
            )

            val db = Firebase.firestore
            val referencia = db.collection("orden")
                .document()
            referencia
                .set(nuevaOrden)
                .addOnSuccessListener {  }
                .addOnFailureListener {  }
        }
    }

    fun eliminacion(){
        val db = Firebase.firestore
        val docRef = db
                .collection("cities")
                .document("BJ")
                .collection("landmarks")
                .document("9V1nMpN797ltUdwdH5Tv")

//        val eliminarCampo = hashMapOf<String, Any>(
//                "name" to FieldValue.delete()
//        )
//        docRef
//                .update(eliminarCampo)
//                .addOnSuccessListener {
//                    Log.i("firebase-delete","${it}")
//                }
//                .addOnFailureListener {
//                    Log.i("firebase-delete","Error eliminando campo ${it}")
//                }

        docRef
                .delete()
                .addOnSuccessListener {
                    Log.i("firebase-delete","${it}")
                }
                .addOnFailureListener {
                    Log.i("firebase-delete","Error eliminando documento ${it}")
                }
    }

    fun leer(){
        val db = Firebase.firestore
        val referencia = db.collectionGroup("landmarks")
        val arregloDocs: ArrayList<DocumentReference>? = null
        referencia
                .whereEqualTo("type","museum")
                .get()
                .addOnSuccessListener {
                    for (landmarks in it){
                        Log.i("firebase-taller","${landmarks.id} ${landmarks.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firebase","Error ${it}")
                }
    }

    fun eliminarDocumentoMedianteConsulta(delId: String) {
        val db = Firebase.firestore
        val referencia = db.collectionGroup("landmarks")
        val arregloDocs = ArrayList<DocumentReference>()
        referencia
                .whereEqualTo("type","museum")
                .get()
                .addOnSuccessListener {
                    for (landmarks in it){
                        arregloDocs.add(landmarks.reference)
                    }
                    if (!delId.isEmpty()) {
                        for (arregloDocs in it) {
                            if (delId.equals(arregloDocs.id)){
                                arregloDocs.reference.delete()
                                Log.i("firebase-taller", "${arregloDocs.id} eliminado")
                                Toast.makeText(applicationContext, "${arregloDocs.id} eliminado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(applicationContext, "Ingrese un id", Toast.LENGTH_SHORT).show()
                        Log.i("firebase-taller", "Ingrese un id")
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firebase","Error ${it}")
                }
    }
}