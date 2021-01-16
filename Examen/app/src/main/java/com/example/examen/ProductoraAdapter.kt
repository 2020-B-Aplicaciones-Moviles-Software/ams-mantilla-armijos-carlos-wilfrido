package com.example.examen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.examen.Productora
import kotlinx.android.synthetic.main.productora.view.*

class ProductoraAdapter(private val mContext: Context, private val listaProductoras: List<Productora>): ArrayAdapter<Productora>(mContext, 0, listaProductoras){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.productora, parent, false)

        val productora = listaProductoras[position]

        layout.tv_nombreProd.text = productora.nombre
        layout.tv_paginaProd.text = productora.pagina
        return layout
    }
}