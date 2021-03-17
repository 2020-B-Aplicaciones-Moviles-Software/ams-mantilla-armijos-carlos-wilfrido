package com.example.amazon2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Item_viewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_viewer)
        supportActionBar?.hide()


        val imageView: ImageView = findViewById(R.id.iv_image)
        val texto: TextView = findViewById(R.id.tv_imagen)
    }
}