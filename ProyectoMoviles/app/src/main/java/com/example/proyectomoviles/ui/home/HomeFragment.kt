package com.example.proyectomoviles.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.proyectomoviles.GridViewAdapter
import com.example.proyectomoviles.NewFragment
import com.example.proyectomoviles.PopularFragment
import com.example.proyectomoviles.R
import com.example.proyectomoviles.utilities.Utils


lateinit var fragmentoActual: Fragment

class HomeFragment : Fragment() {
    private var gridView: GridView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentoNew()
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val botonNew = root.findViewById<Button>(R.id.btn_new)
        val botonPopular = root.findViewById<Button>(R.id.btn_popular)
        botonNew
            ?.setOnClickListener {
                fragmentoNew()
                botonNew.setTextColor(Color.parseColor("#0500FF"))
                botonPopular.setTextColor(Color.parseColor("#606060"))
            }


        botonPopular
            ?.setOnClickListener {
                fragmentoPopular()
                botonPopular.setTextColor(Color.parseColor("#0500FF"))
                botonNew.setTextColor(Color.parseColor("#606060"))
            }


        gridView = root.findViewById<GridView>(R.id.gv_builds)
        gridView?.adapter = GridViewAdapter(requireContext(),Utils.builds())


        gridView?.setOnItemClickListener { parent, v, position, id ->
            Toast.makeText(
                    context, " Clicked Position: " + (position + 1)+" "+ parent[position].background,
                    Toast.LENGTH_SHORT
            ).show()

//            val intent = Intent(context, Item_viewer::class.java)
//            intent.putExtra("POSITION", position)
//            context?.startActivity(intent)
        }
        gridView?.setOnTouchListener { v, event ->
            event.action == MotionEvent.ACTION_MOVE
        }

        return root
    }

    fun fragmentoNew(){
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmento = NewFragment()
        val argumentos = Bundle()
        fragmento.arguments = argumentos

        fragmentTransaction.replace(R.id.rl_fragmento_destacados, fragmento)
        fragmentoActual = fragmento
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun fragmentoPopular(){
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmento = PopularFragment()
        val argumentos = Bundle()
        fragmento.arguments = argumentos

        fragmentTransaction.replace(R.id.rl_fragmento_destacados, fragmento)
        fragmentoActual = fragmento
        fragmentTransaction.commit()
    }
}