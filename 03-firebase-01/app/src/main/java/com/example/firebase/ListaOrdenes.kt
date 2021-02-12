package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.firebase.dto.FirestoreOrdenDto
import com.example.firebase.dto.FirestoreRestauranteDto
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaOrdenes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ordenes)

        val db = Firebase.firestore
        val referencia = db.collection("orden")
        val listaOrdenes = findViewById<ListView>(R.id.lv_ordenes)
        var arregloOrdenes = ArrayList<String>()
        val botonCargarOrdenes = findViewById<Button>(R.id.btn_cargar_ordenes)

            referencia.orderBy("review").limit(2)
                    .get()
                    .addOnSuccessListener {
                        for (orden in it) {
                            Log.i("firebase-consultas", "${orden.id} ${orden.data}")
                            arregloOrdenes.add("${orden.id}")
                        }
                        val adaptador = ArrayAdapter(
                                this,
                                android.R.layout.simple_list_item_1,
                                arregloOrdenes
                        )

                        listaOrdenes.adapter = adaptador
                        Log.i("prueba", "${arregloOrdenes}")
                        var ultimoRegistro: QueryDocumentSnapshot = it.last()

                        botonCargarOrdenes.setOnClickListener {
                            referencia.orderBy("review")
                                    .limit(2)
                                    .startAfter(ultimoRegistro)
                                    .get()
                                    .addOnSuccessListener {
                                        for (orden in it) {
                                            Log.i("firebase-consultas", "${orden.id} ${orden.data}")
                                            arregloOrdenes.add("${orden.id}")
                                        }
                                        listaOrdenes.adapter = adaptador
                                        ultimoRegistro = it.last()
                                    }
                                    .addOnFailureListener {
                                        Log.i("firebase-consultas", "ERROR  ${it}")
                                    }
                        }
                    }
                    .addOnFailureListener {
                        Log.i("firebase-consultas", "ERROR  ${it}")
                    }
    }
}