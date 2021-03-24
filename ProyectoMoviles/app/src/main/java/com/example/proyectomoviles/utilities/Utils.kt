package com.example.proyectomoviles.utilities

import com.example.proyectomoviles.R
import com.google.android.gms.maps.model.LatLng

class Utils {
    companion  object {
        fun drawables(): Array<Int> {
            return arrayOf(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3,
                    R.drawable.logo4, R.drawable.logo5, R.drawable.logo6,
                    R.drawable.logo7, R.drawable.logo8, R.drawable.logo9)
        }
        fun news(): Array<Int> {
            return arrayOf(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3,
                    R.drawable.build7, R.drawable.build8)
        }
        fun populares(): Array<Int> {
            return arrayOf(R.drawable.logo5, R.drawable.logo6,
                R.drawable.logo7, R.drawable.logo8, R.drawable.logo9, R.drawable.build4)
        }
        fun builds(): Array<Int> {
            return arrayOf(R.drawable.logo1, R.drawable.build1, R.drawable.build2,
                    R.drawable.build3, R.drawable.build4, R.drawable.build5,
                    R.drawable.build6, R.drawable.build7, R.drawable.build8,
                    R.drawable.build9)
        }
        fun ubicacion(): LatLng {
            return LatLng(-0.176125, -78.480208)
        }
    }
}