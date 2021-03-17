package com.example.amazon

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Image_viewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        val intent = Intent()
        val imagenId = intent.getParcelableExtra<Bitmap>("SHOW_IMAGE")
        val imageView: ImageView = findViewById(R.id.iv_image)
        imageView.setImageBitmap(imagenId)
    }
}