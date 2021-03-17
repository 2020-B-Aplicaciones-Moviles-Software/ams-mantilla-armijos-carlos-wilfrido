package com.example.amazon.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.amazon2.AccountAdapter
import com.example.amazon2.R

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
//    private var arrayAccount = arrayOf("Pedidos","Configuración de cuenta","Amazon Wallet","Centro de mensajes","Libros de Amazon y 4 estrellas de Amazon","Contenido personalizado","Preferencias de la aplicación")
    private var array1 = arrayOf("1","2","3")
    private var array2 = arrayOf("1","2","3")
    private var array3 = arrayOf("1","2","3")
    private var array4 = arrayOf("1","2","3")
    private var array5 = arrayOf("1","2","3")
    private var array6 = arrayOf("1","2","3")
    private var array7 = arrayOf("1","2","3")
    private var arrayAccount: Map<String, Array<String>> = hashMapOf("Pedidos" to array1, "Configuración de cuenta" to array2, "Amazon Wallet" to array3, "Centro de mensajes" to array4, "Libros de Amazon y 4 estrellas de Amazon" to array5, "Contenido personalizado" to array6, "Preferencias de la aplicación" to array7)


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
                ViewModelProvider(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        accountViewModel.text.observe(viewLifecycleOwner, Observer {

            val listView = root.findViewById<ListView>(R.id.lv_account_top)
            listView.adapter = AccountAdapter(root.context, arrayAccount)

        })
        return root
    }
}