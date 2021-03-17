package com.example.amazon.ui.productos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.amazon.ProductoAdapter
import com.example.amazon.R

private const val ARG_PARAM1 = "imag"

class ProductosFragment : Fragment() {

//    var logos = arrayOf(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4)

    private var imag: String? = null
    private lateinit var productosViewModel: ProductosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imag = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productosViewModel =
                ViewModelProvider(this).get(ProductosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_productos, container, false)
        productosViewModel.text.observe(viewLifecycleOwner, Observer {


            val gridview = root.findViewById<GridView>(R.id.gv_productos)
            gridview.adapter = ProductoAdapter(root.context)


            val textView: TextView = root.findViewById(R.id.textView2)
            textView.setText(R.string.ver_mas)



            gridview.setOnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    root.context, " Clicked Position: " + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null){
            if (imag != null){
//                tvHola?.text = nombre +" "+ edad
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrimerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                ProductosFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}