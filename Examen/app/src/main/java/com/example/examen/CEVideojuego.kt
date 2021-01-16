package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class CEVideojuego : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cevideojuego)

        var idVid: Int? = null
        var idProdActual: Int = 0

        if(intent.hasExtra("videojuego")){
            val videojuego = intent.extras?.getSerializable("videojuego") as Videojuego
            findViewById<EditText>(R.id.et_nombreVideojuego).setText(videojuego.nombre)
            findViewById<EditText>(R.id.et_precioVideojuego).setText(videojuego.precio.toString())
            findViewById<EditText>(R.id.et_fechaPublicaciónVideojuego).setText(SimpleDateFormat("dd-MM-yyyy").format(videojuego.fechaLanzamiento))
            findViewById<Switch>(R.id.sw_enDesarrollo).isChecked = videojuego.enDesarrollo

            idProdActual = videojuego.idProd
            idVid = videojuego.idVid
        }

        val db = ExamenDB.getDatabase(this)

        val guardarVideojuego = findViewById<Button>(R.id.btn_guardarVideojuego)
        guardarVideojuego
            .setOnClickListener {

                val nombreVideojuego = findViewById<EditText>(R.id.et_nombreVideojuego).text.toString()
                val precioVideojuego = findViewById<EditText>(R.id.et_precioVideojuego).text.toString().toDouble()
                val fechaLanzamientoVid = SimpleDateFormat("dd-MM-yyyy").parse(findViewById<EditText>(R.id.et_fechaPublicaciónVideojuego).text.toString())!!
                val enDesarrolloVid = findViewById<Switch>(R.id.sw_enDesarrollo).isChecked



                if(idVid != null){
                    CoroutineScope(Dispatchers.IO).launch {
                        val videojuegoActual = Videojuego(idProdActual, nombreVideojuego, precioVideojuego, fechaLanzamientoVid, enDesarrolloVid)
                        videojuegoActual.idVid = idVid

                        db.videojuegoDAO().update(videojuegoActual)
                        this@CEVideojuego.finish()
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val idProd = intent.getIntExtra("idProd", 0)
                        val crearVideojuego = Videojuego(idProd,nombreVideojuego, precioVideojuego, fechaLanzamientoVid, enDesarrolloVid)

                        db.videojuegoDAO().insert(crearVideojuego)
                        this@CEVideojuego.finish()
                    }
                }
            }
    }
}