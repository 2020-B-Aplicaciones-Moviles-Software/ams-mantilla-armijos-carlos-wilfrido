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

class ListaVideojuegos : AppCompatActivity() {

    var idItemSeleccionado = 0
    private lateinit var db: ExamenDB
    private lateinit var videojuegoSeleccionado: Videojuego
    var listaVacia = emptyList<Videojuego>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_videojuegos)

        var listaVideojuegos = emptyList<Videojuego>()

        db = ExamenDB.getDatabase(this)
        val productora = intent.extras?.getSerializable("productora") as Productora

        this.setTitle(productora.nombre)

        Toast.makeText(this, "Nombre de la Productora: ${productora.nombre}", Toast.LENGTH_SHORT).show()

        val videojuegosPorProductora = db.videojuegoDAO().getAllVideojuegoPorProductora(productora.idProd)

        videojuegosPorProductora.observe(this, Observer {
            listaVideojuegos = it
            val adapter = VideojuegoAdapter(this, listaVideojuegos)
            val lvLista = findViewById<ListView>(R.id.lv_listaVideojuegos)
            lvLista.adapter = adapter

            listaVacia = listaVideojuegos
        })

        val lvLista = findViewById<ListView>(R.id.lv_listaVideojuegos)
        lvLista
            .setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, MostrarVideojuego::class.java)
                intent.putExtra("idVid", listaVideojuegos[position].idVid)
                startActivity(intent)
            }

        val botonCrearVideojuego = findViewById<FloatingActionButton>(R.id.ft_crear_videojuego)
        botonCrearVideojuego
            .setOnClickListener {
                val intent = Intent(this, CEVideojuego::class.java)
                intent.putExtra("idProd", productora.idProd)
                startActivity(intent)
            }

        registerForContextMenu(lvLista)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.menu_videojuego, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        videojuegoSeleccionado = listaVacia[id]
        Toast.makeText(this, "Elemento seleccionado: ${id}", Toast.LENGTH_SHORT).show()
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.actualizarVideojuego -> {
                Toast.makeText(this, "Actualizar a sido seleccionada", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CEVideojuego::class.java)
                intent.putExtra("videojuego", videojuegoSeleccionado)
                startActivity(intent)
            }
            R.id.eliminarVideojuego-> {
                Toast.makeText(this, "Eliminar a sido seleccionada", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    db.videojuegoDAO().delete(videojuegoSeleccionado)
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}