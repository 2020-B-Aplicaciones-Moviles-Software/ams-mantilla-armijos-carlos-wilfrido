package com.example.aplicacionmovilessoftware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import java.util.ArrayList

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val arreglo = arrayListOf(1 ,2, 3, 4, 5)

        val adaptador = ArrayAdapter(
            this,  // Contexto
            android.R.layout.simple_list_item_1,  // como se va a ver (XML)
//            BBaseDeDatos.arregloEnteros  //Arreglo
              BBaseDeDatos.arregloEntrenadores
        )

        listView.adapter = adaptador

        val botonListView = findViewById<Button>(R.id.btn_list_view)
        botonListView
            .setOnClickListener {
                anadirItem(adaptador)
            }
    }

    fun anadirItem(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        BBaseDeDatos.anadirItemAlArreglo(BEntrenador("Ash","Pueblo Paleta"))
        adaptador.notifyDataSetChanged()
    }
}