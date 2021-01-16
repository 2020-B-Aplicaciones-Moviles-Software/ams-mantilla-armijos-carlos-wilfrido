package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.util.*

class MostrarProductora : AppCompatActivity() {

    private lateinit var db: ExamenDB
    private lateinit var productora: Productora
    private lateinit var productoraLiveData: LiveData<Productora>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_productora)

        db = ExamenDB.getDatabase(this)

        val idProd = intent.getIntExtra("idProd", 0)

        productoraLiveData = db.productoraDAO().getById(idProd)

        productoraLiveData.observe(this, androidx.lifecycle.Observer {
            productora = it
            findViewById<TextView>(R.id.tv_nombreMostrarProductora).text = productora.nombre
            findViewById<TextView>(R.id.tv_mostrarPaginaProd).text = productora.pagina
            findViewById<TextView>(R.id.tv_mostrarFechaFundProd).text = formato(productora.fundacion)
            findViewById<TextView>(R.id.tv_mostrarNumEmpleadosProd).text = productora.numeroEmpleados.toString()
        })
    }

    fun formato(fechaFundacion: Date): String{
        val sdf1 = SimpleDateFormat("yyyy-MMM-dd")
        return sdf1.format(fechaFundacion)
    }
}