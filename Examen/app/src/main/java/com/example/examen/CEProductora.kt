package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class CEProductora : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ceproductora)

        var idProd: Int? = null

        if(intent.hasExtra("productora")){
            val productora = intent.extras?.getSerializable("productora") as Productora

            findViewById<EditText>(R.id.et_nombreProd).setText(productora.nombre)
            findViewById<EditText>(R.id.et_paginaProd).setText(productora.pagina)
            findViewById<EditText>(R.id.et_fechaFundProd).setText(SimpleDateFormat("dd-MM-yyyy").format(productora.fundacion))
            findViewById<EditText>(R.id.et_numEmpleadosProd).setText(productora.numeroEmpleados.toString())

            idProd = productora.idProd
        }
        val db = ExamenDB.getDatabase(this)

        val botonGuardar = findViewById<Button>(R.id.btn_guardarProd)
        botonGuardar
            .setOnClickListener {
                val nombreProd = findViewById<EditText>(R.id.et_nombreProd).text.toString()
                val paginaProd= findViewById<EditText>(R.id.et_paginaProd).text.toString()
                val fechaFundProd = SimpleDateFormat("dd-MM-yyyy").parse(findViewById<EditText>(R.id.et_fechaFundProd).text.toString())
                val numEmpleadosProd= findViewById<EditText>(R.id.et_numEmpleadosProd).text.toString().toInt()
                val productora = Productora(nombreProd,paginaProd,fechaFundProd,numEmpleadosProd)

                if(idProd != null){
                    CoroutineScope(Dispatchers.IO).launch {
                        productora.idProd = idProd

                        db.productoraDAO().update(productora)

                        this@CEProductora.finish()
                    }
                }else{
                    CoroutineScope(Dispatchers.IO).launch {
                        db.productoraDAO().insertAll(productora)

                        this@CEProductora.finish()
                    }
                }
            }
    }
}