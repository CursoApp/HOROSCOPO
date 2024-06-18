package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }

    private lateinit var session:SessionManager

    private var currentHoroscopeIndex:Int = -1
    private var horoscopeId:String? = null
    private lateinit var horoscope:Horoscope
    private var isFavorite:Boolean = false
    private var dailyLuckText:String? = null

    private lateinit var horoscopeTextView:TextView
    private lateinit var horoscopeImageView:ImageView
    private lateinit var horoscopeLuckTextView:TextView
    private lateinit var progress:ProgressBar
    private lateinit var prevButton:Button
    private lateinit var nextButton:Button

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private lateinit var favoriteMenuItem: MenuItem
    private var shareMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*Añadido de clase, lo de arriba se crea por defecto.*/

        session = SessionManager(this)

        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!


        horoscope = HoroscopeProvider.findById(id)!!

        isFavorite = session.favoriteHoroscope.equals(horoscope.id) ?: false

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)

        textView.setText(horoscope.name)
        imageView.setImageResource(horoscope.logo)

        //findViewById<TextView>(R.id.textView).setText(horoscope.name)
        //findViewById<ImageView>(R.id.imageView).setImageResource(horoscope.logo)


    }
    fun setFavoriteIcon () {
        if (isFavorite) {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite_selected)
        } else {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)
        favoriteMenuItem = menu.findItem(R.id.menu_favorite)
        setFavoriteIcon()
        return true
    }

    /* Lo de abajo es para que se muestren los favoritos encima de los signos zodiacales*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                if (isFavorite) {
                    session.favoriteHoroscope = ""
                } else {
                    session.favoriteHoroscope = horoscope.id
                }
                isFavorite = !isFavorite
                setFavoriteIcon()
                true

                //Log.i("MENU", "He hecho click en el menu de favorito")
            }

            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
                //Log.i("MENU", "He hecho click en el menu de compartir")
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

        //nameTextView = findViewById(R.id.textView)
        //nameTextView.setText(horoscope.name)

        //nameTextView.setBackgroundResource(horoscope.color) /*Este parametro es para el color del fondo*/

        //nameTextView.setBackgroundResource(horoscope.color) /*Este parametro es para el color*/


        //findViewById<ImageView>(R.id.imageView).setImageResource(horoscope.logo)


// Esto estaba en Pto.24 findViewById<TextView>(R.id.textView).setText(name)  Y findViewById<ImageView>(R.id.imageView).setImageResource(logo)

// Esto estaba en Pto.33 val name = intent.getIntExtra("HOROSCOPE_NAME", -1)  Y val logo = intent.getIntExtra("HOROSCOPE_LOGO", -1)

