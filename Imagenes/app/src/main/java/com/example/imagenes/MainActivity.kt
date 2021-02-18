package com.example.imagenes

import android.R.attr
import android.app.PendingIntent.getActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 2
    val SELECT_FILE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonSubir = findViewById<Button>(R.id.btn_subir)

        botonSubir
            .setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                startActivityForResult(Intent.createChooser(intent,"Seleccione una imagen"), SELECT_FILE)
            }

        val botonTomar = findViewById<Button>(R.id.btn_tomar)

        botonTomar
            .setOnClickListener {
                intentTomarFoto()
            }
    }

    private fun intentTomarFoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imagen = findViewById<ImageView>(R.id.iv_imagen)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val img = data?.extras?.get("data") as Bitmap
            imagen.setImageBitmap(img)
        }else if (requestCode == SELECT_FILE && resultCode == RESULT_OK){
            imagen.setImageURI(data?.data)
        }

    }
}