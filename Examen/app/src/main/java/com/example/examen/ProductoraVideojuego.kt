package com.example.examen

import androidx.room.Embedded
import androidx.room.Relation

data class ProductoraVideojuego(
    @Embedded val productora: Productora,
    @Relation(
        parentColumn = "idProd",
        entityColumn = "idProd"
    )
    val videojuegos: List<Videojuego>
) {
}