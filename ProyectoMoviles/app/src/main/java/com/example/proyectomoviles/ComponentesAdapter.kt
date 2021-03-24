package com.example.proyectomoviles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView

class ComponentesAdapter(private val context: Context, private val elementos: Array<Array<Int>>): RecyclerView.Adapter<ComponentesAdapter.PageHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): PageHolder  =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.page_layout, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        when(position) {
            0 -> holder.gridView.adapter = GridViewAdapter(context, elementos[0])
            1 -> holder.gridView.adapter = GridViewAdapter(context, elementos[1])
            2 -> holder.gridView.adapter = GridViewAdapter(context, elementos[2])
            3 -> holder.gridView.adapter = GridViewAdapter(context, elementos[3])
            4 -> holder.gridView.adapter = GridViewAdapter(context, elementos[4])
        }
    }

    override fun getItemCount(): Int = 5

    inner class PageHolder(view: View): RecyclerView.ViewHolder(view){
        val gridView: GridView = view.findViewById(R.id.gv_productos_categoria)
    }
}