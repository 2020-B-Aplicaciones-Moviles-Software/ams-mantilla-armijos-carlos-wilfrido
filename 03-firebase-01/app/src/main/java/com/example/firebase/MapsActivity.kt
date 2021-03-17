package com.example.firebase

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val botonCarolina =
            findViewById<Button>(R.id.btn_ir_carolina)
        botonCarolina
            .setOnClickListener {
                val quicentro = LatLng(-0.176125, -78.480208)
                val zoom = 17f
                moverCamaraCopnZoom(quicentro,zoom)
            }

    }
    override fun onMapReady(googleMap: GoogleMap?){
        if (googleMap != null){
            mMap = googleMap

            mMap
                .setOnPolygonClickListener {
                    Log.i("mapa","setOnPoligonListener ${it}")
                }
            mMap.
                setOnPolylineClickListener {
                    Log.i("mapa","setOnPolylineClickListener ${it}")
                }
            mMap.
                setOnMarkerClickListener {
                    Log.i("mapa","setOnMarkerClickListener ${it}")
                    return@setOnMarkerClickListener true
                }
            mMap.
                setOnCameraMoveListener {
                    Log.i("mapa","setOnCameraMoveListener")
                }
            mMap.
                setOnCameraMoveStartedListener {
                    Log.i("mapa","setOnCameraMoveStartedListener ${it}")
                }
            mMap.
                setOnCameraIdleListener {
                    Log.i("mapa","setOnCameraIdleListener")
                }




            establecerConfiguracionMapa(mMap)
            val quicentro = LatLng(-0.176125, -78.480208)
            val titulo = "Quicentro"
            val zoom = 17f
            anadirMarcador(quicentro,titulo)
            moverCamaraCopnZoom(quicentro,zoom)

            //LINEA
            val poliLineaUno = googleMap
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.122290, -78.506510),
                            LatLng(-0.123631, -78.506945),
                            LatLng(-0.123540, -78.504831)
                        )
                )
            poliLineaUno.tag = "linea-1"

            //POLIGONO
            val poligonoUno = googleMap
                .addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.126206, -78.504734),
                            LatLng(-0.124039, -78.504847),
                            LatLng(-0.124232, -78.506864)
                        )
                )
            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag = "poligono-2"
        }
    }
    
    fun anadirMarcador(latlng: LatLng, title: String){
        mMap.addMarker(
            MarkerOptions()
                .position(latlng)
                .title(title)
        )
    }

    fun moverCamaraCopnZoom(latlng: LatLng, zoom: Float= 10f){
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latlng,zoom)
        )
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
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
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            Log.i("mapa","Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }
}