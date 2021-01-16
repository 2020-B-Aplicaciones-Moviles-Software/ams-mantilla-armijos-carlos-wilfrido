package com.example.examen

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.util.*

@Entity(tableName = "videojuego")
class Videojuego (

    @ColumnInfo(name = "idProd") var idProd: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "precio") val precio: Double,
    @ColumnInfo(name = "fec_lanzamiento")
    @TypeConverters(DateTypeConverter::class) val fechaLanzamiento: Date,
    @ColumnInfo(name = "en_desarrollo") val enDesarrollo: Boolean,
    @PrimaryKey (autoGenerate = true) var idVid: Int = 0
): Serializable