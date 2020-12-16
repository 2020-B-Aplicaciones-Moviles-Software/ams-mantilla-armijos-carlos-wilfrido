package com.example.aplicacionmovilessoftware

import android.os.Parcel
import android.os.Parcelable

class BEntrenador (
        var nombre: String?,
        var descripcion: String?,
        val liga: DLiga?
):
        Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(DLiga::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)  // Elvis operator ?:
        dest?.writeString(descripcion)
        dest?.writeParcelable(liga, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}