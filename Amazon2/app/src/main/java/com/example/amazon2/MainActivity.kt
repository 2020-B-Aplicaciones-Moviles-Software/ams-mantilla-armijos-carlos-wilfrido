package com.example.amazon2

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.amazon2.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var fragmentoActual: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        homeFrag()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun crearFragmentoUno(){
        //Manager
        val fragmentManager = supportFragmentManager
        //Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        //Crear instancia de fragmento
        val primerFragmento = HomeFragment()
        //Argumentos
        val argumentos = Bundle()
        argumentos.putString("nombre","Carlos Mantilla")
        argumentos.putInt("edad",29)
        primerFragmento.arguments = argumentos

        //Añadir elemento
        fragmentTransaction.replace(R.id.nav_host_fragment, primerFragmento)
        fragmentoActual = primerFragmento
        fragmentTransaction.commit()
    }

    fun homeFrag(){
        //Manager
        val fragmentManager = supportFragmentManager
        //Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        //Crear instancia de fragmento
        val primerFragmento = HomeFragment()
        //Argumentos
        val argumentos = Bundle()
        primerFragmento.arguments = argumentos

        //Añadir elemento
        fragmentTransaction.replace(R.id.contenedor_categorias, primerFragmento)
        fragmentoActual = primerFragmento
        fragmentTransaction.commit()
    }
}