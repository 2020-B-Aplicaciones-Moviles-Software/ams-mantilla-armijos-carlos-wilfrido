package com.example.firebase.dto

class FirestoreRestauranteDto (
    var uid: String = "",
    var nombre: String = ""
){
    override fun toString(): String {
        return nombre
    }
}