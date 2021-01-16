package com.example.examen

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.util.*


@Entity(tableName = "productora")
class Productora (

        @ColumnInfo(name = "nombre") val nombre: String,
        @ColumnInfo(name = "pagina") val pagina: String,
        @ColumnInfo(name = "fundacion")
        @TypeConverters(DateTypeConverter::class) val fundacion: Date,
        @ColumnInfo(name = "num_empleados") val numeroEmpleados: Int,
        @PrimaryKey(autoGenerate = true) var idProd: Int = 0
):Serializable