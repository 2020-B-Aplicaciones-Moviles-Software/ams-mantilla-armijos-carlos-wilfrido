package com.example.amazon2.utilities

import com.example.amazon2.R
import com.google.android.gms.maps.model.LatLng

class Utils {
    companion  object {
        fun drawables(): Array<Int> {
            val imagenes = arrayOf(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3,
                    R.drawable.logo4, R.drawable.logo5, R.drawable.logo6,
                    R.drawable.logo7, R.drawable.logo8, R.drawable.logo9)
            return imagenes
        }

        fun ubicaciones(): Array<LatLng> {
                val coordenadas = arrayOf(LatLng(-0.176125, -78.480208),LatLng(-0.111729, -78.501830),LatLng(-0.113327, -78.490371),
                    LatLng(-0.125631, -78.474941),LatLng(-0.145215, -78.463039),LatLng(-0.159792, -78.464992),
                    LatLng(-0.168203, -78.470530),LatLng(-0.169364, -78.484439),LatLng(-0.180666, -78.508487))
            return coordenadas
        }
    }
}