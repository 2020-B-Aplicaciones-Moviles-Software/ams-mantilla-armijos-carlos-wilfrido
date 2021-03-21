package com.example.amazon2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.get

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "drawable"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var drawable: String? = null
    private var gridView: GridView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            drawable = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_producto, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        gridView = getView()?.findViewById<GridView>(R.id.gv_productos)
        gridView?.adapter = ProductoAdapter(requireContext())

        val textView: TextView? = getView()?.findViewById(R.id.textView2)
        textView?.setText(R.string.ver_mas)

        gridView?.setOnItemClickListener { parent, v, position, id ->
            Toast.makeText(
                context, " Clicked Position: " + (position + 1)+" "+parent.get(position).background,
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(context, Item_viewer::class.java)
            intent.putExtra("POSITION", position)
            context?.startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProductoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}