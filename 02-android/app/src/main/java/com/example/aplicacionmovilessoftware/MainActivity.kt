package com.example.aplicacionmovilessoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val botonIrCIntentExplicitoParametros = findViewById<Button>(R.id.btn_ir_intent_explicito_parametros)
        botonIrCIntentExplicitoParametros
            .setOnClickListener {
                val parametros = arrayListOf<Pair<String,*>>(
                    Pair("nombre","Carlos"),
                    Pair("apellido","Mantilla"),
                    Pair("edad", 28)
                )
                irAActividad(CIntentExplicitoParametros::class.java, parametros)
            }
    }

    fun irAActividad(
        clase: Class<*>,
        parametros: ArrayList<Pair<String,*>>?=null,
        codigo: Int? = null
    ) {
        val intentExplicito = Intent(
                this,
                clase
        )
        if (parametros != null){
            parametros.forEach {
                val nombreVariable = it.first
                val valorVariable = it.second

                var tipoDato = false

                tipoDato = it.second is String
                if (tipoDato == true){
                    intentExplicito.putExtra(nombreVariable, valorVariable as String)
                    tipoDato = false
                }

                tipoDato = it.second is Int
                if (tipoDato == true){
                    intentExplicito.putExtra(nombreVariable, valorVariable as Int)
                    tipoDato = false
                }

            }
        }
        if (codigo != null){
            startActivityForResult(intentExplicito, codigo)
        }else{
            startActivity(intentExplicito)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            102 -> {
                if (resultCode == RESULT_OK){

                    if (data != null){

                        val nombre = data.getStringExtra("nombre")
                        val edad = data.getIntExtra("edad",0)

                        Log.i("intent-explicito","Nombre: ${nombre} Edad: ${edad}")
                    }else{
                        //CODIGO SIN REDULTADO OK
                    }

                }else{
                    Log.i("intent-explicito","Usuario no llenó los datos")
                }
            }
        }
    }
}