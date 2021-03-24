package com.example.proyectomoviles

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectomoviles.utilities.*


class ProductoPopularAdapter(
    private val productos: Array<Int>,
    private val contexto: PopularFragment,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<
        ProductoPopularAdapter.MyViewHolder
        >() {
    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view){

        val productoImagen: ImageView

        init {
            productoImagen = view.findViewById(R.id.product_image)
            productoImagen.setOnClickListener {
                Toast.makeText(contexto.context,"Click", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductoPopularAdapter.MyViewHolder {
        //Definimos la interfaz que vamos a usar
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.product_item,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(
        holder: ProductoPopularAdapter.MyViewHolder,
        position: Int
    ) {
        val producto = productos[position]
        holder.productoImagen.setImageResource(producto)
    }


}