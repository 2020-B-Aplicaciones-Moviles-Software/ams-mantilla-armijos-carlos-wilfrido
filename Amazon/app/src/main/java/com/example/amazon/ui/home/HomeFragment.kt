package com.example.amazon.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.amazon.ProductosViewModel
import com.example.amazon.R


class HomeFragment : Fragment() {

    var dots = arrayListOf<GridView>()
    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        dots.add(root.findViewById(R.id.gv_productos))
        val lv = view?.findViewById<ListView>(R.id.lv_destacados_categorias)

        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,dots)
        lv?.adapter = arrayAdapter
        registerForContextMenu(lv!!)
        //val view = root.findViewById<RelativeLayout>(R.id.fl_frame)

        view?.setBackgroundColor(R.color.black)
        return root
    }
}