package com.example.amazon2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.amazon2.utilities.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Item_viewer : AppCompatActivity(),
    OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_viewer)
        supportActionBar?.hide()

        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val imagenes: Array<Int> = Utils.drawables()
        val posicion:  Int = intent.getIntExtra("POSITION",0)

        val imageView: ImageView = findViewById(R.id.iv_image)

        imageView.setImageResource(imagenes[posicion])
    }

    override fun onMapReady(googleMap: GoogleMap?){
        if (googleMap != null){
            mMap = googleMap

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED) {
                return
            }


            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()

            val location: Location? = locationManager.getLastKnownLocation(
                locationManager
                    .getBestProvider(criteria, false)!!
            )
            val latitude: Double? = location?.latitude
            val longitud: Double? = location?.longitude


            mMap.
            setOnMarkerClickListener {
                Log.i("mapa", "setOnMarkerClickListener ${it}")
                return@setOnMarkerClickListener true
            }
            mMap.
            setOnCameraMoveListener {
                Log.i("mapa", "setOnCameraMoveListener")
            }
            mMap.
            setOnCameraMoveStartedListener {
                Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
            }
            mMap.
            setOnCameraIdleListener {
                Log.i("mapa", "setOnCameraIdleListener")
            }


            establecerConfiguracionMapa(mMap)
            val ubicaciones: Array<LatLng> = Utils.ubicaciones()
            val posicion = intent.getIntExtra("POSITION", 0)
            val newLat = ubicaciones[posicion].latitude
            val newLng = ubicaciones[posicion].longitude
            val zoom = 17f
            anadirMarcador(ubicaciones[posicion], "Ubicación producto")
            moverCamaraCopnZoom(ubicaciones[posicion], zoom)


            val botonMapa = findViewById<Button>(R.id.btn_ir_mapa)
            botonMapa
                .setOnClickListener {
                    showDirections(latitude!!,longitud!!,newLat,newLng)
                }

        }
    }

    fun anadirMarcador(latlng: LatLng, title: String){
        mMap.addMarker(
            MarkerOptions()
                .position(latlng)
                .title(title)
        )
    }

    fun moverCamaraCopnZoom(latlng: LatLng, zoom: Float = 10f){
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latlng, zoom)
        )
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            Log.i("mapa", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }

    fun showDirections(lat: Double, lng: Double, lat1: Double, lng1: Double) {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "http://maps.google.com/maps?" +
                        "saddr=" + lat + "," + lng + "&daddr=" + lat1 + "," +
                        lng1
            )
        )
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
        startActivity(intent)
    }
}