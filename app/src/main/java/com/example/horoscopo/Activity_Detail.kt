package com.example.horoscopo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Activity_Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*Añadido de clase, lo de arriba se crea por defecto.*/
        val id = intent.getStringExtra("HOROSCOPE_ID")

        //val name = intent.getIntExtra("HOROSCOPE_NAME", -1)
        //val logo = intent.getIntExtra("HOROSCOPE_LOGO", -1)

        findViewById<TextView>(R.id.textView).setText(name)
        findViewById<ImageView>(R.id.imageView).setImageResource(logo)
    }
}