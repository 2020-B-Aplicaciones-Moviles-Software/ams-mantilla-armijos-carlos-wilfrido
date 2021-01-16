package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var idItemSeleccionado = 0
    private lateinit var productoraSeleccionada: Productora
    var listaVacia = emptyList<Productora>()
    private lateinit var db: ExamenDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaProductoras = emptyList<Productora>()
        db = ExamenDB.getDatabase(this)

        db.productoraDAO().getAll().observe(this,
                Observer {
                    listaProductoras = it
                    val adapter = ProductoraAdapter(this, listaProductoras)
                    val lista =findViewById<ListView>(R.id.lv_productoras)
                    lista.adapter = adapter

                    listaVacia = listaProductoras
        })

        val listaProds = findViewById<ListView>(R.id.lv_productoras)
        listaProds
            .setOnItemClickListener{parent,view,position,id ->
                val intent = Intent(this, MostrarProductora::class.java)
                intent.putExtra("idProd", listaProductoras[position].idProd)
                startActivity(intent)
            }
        val botonCrearProductora = findViewById<FloatingActionButton>(R.id.fbtn_crear_productora)
        botonCrearProductora
            .setOnClickListener{
                val intent = Intent(this, CEProductora::class.java)
                startActivity(intent)
            }
        registerForContextMenu(listaProds)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.menu_productoras, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        productoraSeleccionada = listaVacia[id]
        Toast.makeText(this, "Elemento seleccionado: ${id}", Toast.LENGTH_SHORT).show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.prod_ver_videojuegos -> {
                Toast.makeText(this, "Mostrar videojuegos a sido seleccionado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListaVideojuegos::class.java)
                intent.putExtra("productora", productoraSeleccionada)
                startActivity(intent)
            }
            R.id.prod_editar -> {
                Toast.makeText(this, "Actualizar a sido seleccionada", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CEProductora::class.java)
                intent.putExtra("productora", productoraSeleccionada)
                startActivity(intent)
            }
            R.id.prod_eliminar-> {
                Toast.makeText(this, "Eliminar a sido seleccionada", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    db.productoraDAO().delete(productoraSeleccionada)
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}