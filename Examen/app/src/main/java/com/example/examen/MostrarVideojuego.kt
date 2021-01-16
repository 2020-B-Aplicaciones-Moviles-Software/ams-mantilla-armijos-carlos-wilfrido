package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.*

class MostrarVideojuego : AppCompatActivity() {
    private lateinit var db: ExamenDB
    private lateinit var videojuego: Videojuego
    private lateinit var videojuegoLiveData: LiveData<Videojuego>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_videojuego)

        db = ExamenDB.getDatabase(this)

        val idVid = intent.getIntExtra("idVid", 0)
        videojuegoLiveData = db.videojuegoDAO().getById(idVid)

        videojuegoLiveData.observe(this, Observer {
            videojuego = it

            findViewById<TextView>(R.id.tv_nombreMostrarVideojuego).text = videojuego.nombre
            findViewById<TextView>(R.id.tv_mostrarPrecioVideojuego).text = "$ ${videojuego.precio}"
            findViewById<TextView>(R.id.tv_mostrarFechaLanzamiento).text = formato(videojuego.fechaLanzamiento)
            findViewById<TextView>(R.id.tv_mostrarEnDesarrollo).text = enDesarrollo(videojuego.enDesarrollo)
        })
    }
    fun enDesarrollo(enDesarrollo:Boolean): String{
        return if(enDesarrollo) "En desarrollo" else "Lanzado"
    }

    fun formato(fechaLanzamiento: Date): String{
        val sdf1 = SimpleDateFormat("dd-MMM-yyyy")
        return sdf1.format(fechaLanzamiento)
    }
}