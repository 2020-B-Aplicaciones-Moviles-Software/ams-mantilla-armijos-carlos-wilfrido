package com.example.aplicacionmovilessoftware

class BEntrenador (nombre: String, descripcion: String){
    // propiedad Nombre public String
    // propiedad Descripcion public String
    public var nombre: String = nombre
    public var descripcion: String = descripcion

    override fun toString(): String {
        return "${nombre}: \n ${descripcion}"
    }

}