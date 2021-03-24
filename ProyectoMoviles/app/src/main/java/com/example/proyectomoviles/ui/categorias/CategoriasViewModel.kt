package com.example.proyectomoviles.ui.categorias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento categoría"
    }
    val text: LiveData<String> = _text
}