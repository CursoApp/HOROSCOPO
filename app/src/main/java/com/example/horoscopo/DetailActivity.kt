package com.example.horoscopo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }

    lateinit var horoscope: Horoscope
    lateinit var nameTextView: TextView
    /*Este parametro es para el color*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*Añadido de clase, lo de arriba se crea por defecto.*/


        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!


        horoscope = HoroscopeProvider.findById(id)!!

        nameTextView = findViewById(R.id.textView)
        nameTextView.setText(horoscope.name)
        nameTextView.setBackgroundResource(horoscope.color) /*Este parametro es para el color del fondo*/

        findViewById<ImageView>(R.id.imageView).setImageResource(horoscope.logo)
    }
}

// Esto estaba en Pto.24 findViewById<TextView>(R.id.textView).setText(name)  Y findViewById<ImageView>(R.id.imageView).setImageResource(logo)

// Esto estaba en Pto.33 val name = intent.getIntExtra("HOROSCOPE_NAME", -1)  Y val logo = intent.getIntExtra("HOROSCOPE_LOGO", -1)

