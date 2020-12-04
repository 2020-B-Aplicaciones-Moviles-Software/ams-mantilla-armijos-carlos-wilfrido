package com.example.aplicacionmovilessoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BBaseDeDatos.inicializarArreglo()

        val botonCicloVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonCicloVida
                .setOnClickListener {
                    irAActividad(ACicloVida::class.java)
                }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irAActividad(BListView::class.java)
            }
    }

    fun irAActividad(clase: Class<*>) {
        val intentExplicito = Intent(
                this,
                clase
        )
        startActivity(intentExplicito)
    }
}