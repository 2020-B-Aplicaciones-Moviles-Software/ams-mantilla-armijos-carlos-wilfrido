package com.example.proyectomoviles.ui.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectomoviles.ComponentesAdapter
import com.example.proyectomoviles.R
import com.example.proyectomoviles.utilities.Utils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CategoriasFragment : Fragment() {

    private lateinit var adaptador: ComponentesAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categorias, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nombres = arrayOf("Mainboard","Procesadores","Almacenamiento","Gráficas","Gabinetes")
        val elementos = arrayOf(Utils.drawables(),Utils.news(),Utils.builds())
        adaptador = ComponentesAdapter(requireContext(),elementos)
        viewPager = view.findViewById(R.id.vp_tabs)
        viewPager.adapter = adaptador

        val tabLayout = view.findViewById<TabLayout>(R.id.tl_componentes)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = nombres[position]
        }.attach()


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@CategoriasFragment.requireContext(), "Tab ${tab?.text} selected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@CategoriasFragment.requireContext(), "Tab ${tab?.text} unselected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@CategoriasFragment.requireContext(), "Tab ${tab?.text} reselected", Toast.LENGTH_SHORT).show()
            }
        })
    }


}


