package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

lateinit var fragmentoActual: Fragment

class E_Fragmento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e__fragmento)

        val botonPrimer = findViewById<Button>(R.id.btn_primer_fragmento)
        botonPrimer
            .setOnClickListener { crearFragmentoUno() }

        val botonSegundo = findViewById<Button>(R.id.btn_segundo_fragmento)
        botonSegundo
            .setOnClickListener { crearFragmentoDos() }
    }

    fun crearFragmentoUno(){
        //Manager
        val fragmentManager = supportFragmentManager
        //Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        //Crear instancia de fragmento
        val primerFragmento = PrimerFragment()
        //Argumentos
        val argumentos = Bundle()
        argumentos.putString("nombre","Carlos Mantilla")
        argumentos.putInt("edad",29)
        primerFragmento.arguments = argumentos

        //Añadir elemento
        fragmentTransaction.replace(R.id.rl_fragmento, primerFragmento)
        fragmentoActual = primerFragmento
        fragmentTransaction.commit()
    }

    fun crearFragmentoDos(){
        //Manager
        val fragmentManager = supportFragmentManager
        //Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        //Crear instancia de fragmento
        val segundoFragmento = SegundoFragment()
        //Argumentos
        val argumentos = Bundle()
        segundoFragmento.arguments = argumentos

        //Añadir elemento
        fragmentTransaction.replace(R.id.rl_fragmento, segundoFragmento)
        fragmentoActual = segundoFragmento
        fragmentTransaction.commit()
    }
}