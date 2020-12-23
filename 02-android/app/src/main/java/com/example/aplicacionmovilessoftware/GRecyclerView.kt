package com.example.aplicacionmovilessoftware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_recycler_view)
        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador
            .add(
                BEntrenador("Carlos",
                    "1720195393",
                    DLiga("Kanto","Liga Pokemon")
                )
            )
        listaEntrenador
            .add(
                BEntrenador("Vicente",
                    "1741521369",
                    DLiga("Johto","Liga Pokemon"))
            )

        val recyclerViewBEntrenador = findViewById<RecyclerView>(
            R.id.rv_entrenador
        )
        this.iniciarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewBEntrenador
        )
    }
    fun iniciarRecyclerView(
        lista:List<BEntrenador>,
        actividad: GRecyclerView,
        recyclerView: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            lista,
            actividad,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptador.notifyDataSetChanged()
    }

    fun anadirLikesTotal(){
        val textoLikes = findViewById<TextView>(R.id.tv_likes_completo)
        totalLikes = totalLikes + 1
        textoLikes.text = totalLikes.toString()
    }
}