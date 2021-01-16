package com.example.examen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.videojuego.view.*

class VideojuegoAdapter (private val mContext: Context, private val listaVideojuegos: List<Videojuego>):
    ArrayAdapter<Videojuego>(mContext, 0, listaVideojuegos){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutVideojuego = LayoutInflater.from(mContext).inflate(R.layout.videojuego, parent, false)

        val videojuego = listaVideojuegos[position]
        layoutVideojuego.tv_nombreVideojuego.text = videojuego.nombre
        layoutVideojuego.tv_precioVideojuego.text = "$ ${videojuego.precio}"
        layoutVideojuego.tv_enDesarrollo.text = enDesarrollo(videojuego.enDesarrollo)

        return layoutVideojuego
    }

    fun enDesarrollo(enDesarrollo:Boolean): String{
        return if(enDesarrollo) "En Desarrollo" else "Lanzado"
    }
}