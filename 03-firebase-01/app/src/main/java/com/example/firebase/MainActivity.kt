package com.example.firebase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    val CODIGO_INGRESO = 102
    val mensajeNoLogueado = "Dale clic al boton Ingresar"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIngresar = findViewById<Button>(R.id.btn_ingresar)
        botonIngresar
            .setOnClickListener {
                pedidoIngresar()
            }

        val botonSalir = findViewById<Button>(R.id.btn_salir)
        botonSalir
            .setOnClickListener {
                pedidoSalir()
            }

        val texto = findViewById<TextView>(R.id.textView)

        val instanciaAuth = FirebaseAuth.getInstance()
        if (instanciaAuth.currentUser != null){
            texto.text = "Bienvenido ${instanciaAuth.currentUser?.email}"
            setearUsuarioFirebase()
        }else{
            texto.text = "Dale clic al boton ingresar"
        }
        mostrarBotonesOcultos()

        val botonFirestore = findViewById<Button>(R.id.btn_irFirestore)
        botonFirestore
                .setOnClickListener {
                    irActividad(BFirestore::class.java)
                }
    }
    fun pedidoIngresar(){

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()

        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.Theme_AppCompat)
                .setLogo(R.drawable.logo)
                .setTosAndPrivacyPolicyUrls("https://example.com/terms.html",
                "https://example.com/privacy.html"
                )
                .build(),
            CODIGO_INGRESO)
    }

    fun pedidoSalir(){
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Log.i("firebase-firestore", "Salio")
                val texto = findViewById<TextView>(R.id.textView)
                texto.text = mensajeNoLogueado
                BAuthUsuario.usuario = null
                mostrarBotonesOcultos()
            }
            .addOnFailureListener {
                Log.i("firebase-firestore","Hubo problemas en salir")
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CODIGO_INGRESO -> {
                if (resultCode == Activity.RESULT_OK){
                    val usuario = IdpResponse.fromResultIntent(data)
                    if(usuario?.isNewUser == true){
                        //Lógica para crear el usuario en nuestra colección
                    }
                    val texto = findViewById<TextView>(R.id.textView)
                    texto.text = "Bienvenido ${usuario?.email}"
                    setearUsuarioFirebase()
                    mostrarBotonesOcultos()
                }else{
                    Log.i("firebase-login","EL usuario canceló")
                }
            }
        }
    }

    fun setearUsuarioFirebase(){
        var instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser

        if(usuarioLocal != null){
            if(usuarioLocal.email != null){
                val usuarioFirebase = BUsuarioFirebase(
                        usuarioLocal.uid,
                        usuarioLocal.email!!
                )
                BAuthUsuario.usuario = usuarioFirebase
            }
        }
    }

    fun mostrarBotonesOcultos(){
        val botonEscondidoFirestore = findViewById<Button>(R.id.btn_irFirestore)

        if(BAuthUsuario.usuario != null){
            botonEscondidoFirestore.visibility = View.VISIBLE
        }else{
            botonEscondidoFirestore.visibility = View.INVISIBLE
        }
    }

    fun irActividad(
            clase: Class<*>,
            parametros: ArrayList<Pair<String, *>>? = null,
            codigo: Int? = null
    ) {
        val intentExplicito = Intent(
                this,
                clase
        )
        if (parametros != null) {
            parametros.forEach {
                val nombreVariable = it.first
                val valorVariable = it.second

                var tipoDato = false

                tipoDato = it.second is String // instanceOf()
                if (tipoDato == true) {
                    intentExplicito.putExtra(nombreVariable, valorVariable as String)
                }
                tipoDato = it.second is Int // instanceOf()
                if (tipoDato == true) {
                    intentExplicito.putExtra(nombreVariable, valorVariable as Int)
                }
                tipoDato = it.second is Parcelable // instanceOf()
                if (tipoDato == true) {
                    intentExplicito.putExtra(nombreVariable, valorVariable as Parcelable)
                }
            }
        }
        if (codigo != null) {
            startActivityForResult(intentExplicito, codigo)
        } else {
            startActivity(intentExplicito)
        }
    }
}