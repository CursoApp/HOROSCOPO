package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.utils.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }

    lateinit var horoscopo: Horoscope
    var isFavorite = false

    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var navigationBar: BottomNavigationView
    lateinit var progressIndicator: LinearProgressIndicator
    lateinit var dailyHoroscopeTextView: TextView

    lateinit var favoriteMenuItem: MenuItem

    private lateinit var session:SessionManager

    //private var currentHoroscopeIndex:Int = -1
    //private var horoscopeId:String? = null
    //private lateinit var horoscope:Horoscope
   // private var isFavorite:Boolean = false
    //private var dailyLuckText:String? = null

    /*private lateinit var horoscopeTextView:TextView
    private lateinit var horoscopeImageView:ImageView
    private lateinit var horoscopeLuckTextView:TextView
    private lateinit var progress:ProgressBar
    private lateinit var prevButton:Button
    private lateinit var nextButton:Button*/

    /*private lateinit var textView: TextView
    private lateinit var imageView: ImageView*/

    /*private lateinit var favoriteMenuItem: MenuItem
    private var shareMenuItem: MenuItem? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*Añadido de clase, lo de arriba se crea por defecto.*/

        session = SessionManager(this)

        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!


        horoscopo = HoroscopeProvider.findById(id)!!

        isFavorite = session.isFavorite(horoscopo.id)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        navigationBar = findViewById(R.id.navigationBar)
        progressIndicator = findViewById(R.id.progressIndicator)
        dailyHoroscopeTextView = findViewById(R.id.dailyHoroscopeTextView)
        //Esto de arriba hará que la App cargue el horoscopo diario

        textView.setText(horoscopo.name)
        imageView.setImageResource(horoscopo.logo)

        supportActionBar?.setTitle(horoscopo.name)
        supportActionBar?.setSubtitle(horoscopo.description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //findViewById<TextView>(R.id.textView).setText(horoscope.name)
        //findViewById<ImageView>(R.id.imageView).setImageResource(horoscope.logo)

        // **** Esto añadido el 21Jun24 ====

        navigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_daily -> {
                    getHoroscopeLuck("daily")
                }
                R.id.menu_weekly -> {
                    getHoroscopeLuck("weekly")
                }
                R.id.menu_monthly -> {
                    getHoroscopeLuck("monthly")
                }
            }

            return@setOnItemSelectedListener true
        }

        navigationBar.selectedItemId = R.id.menu_daily
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

            android.R.id.home -> {/* Este código es para dar valor a la flecha de regresar a la primera páguna*/
                finish()
                true
            }

            R.id.menu_favorite -> {
                if (isFavorite) {
                    session.setFavoriteHoroscope("")
                } else {
                    session.setFavoriteHoroscope(horoscopo.id)
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

    fun getHoroscopeLuck(method: String) {
        progressIndicator.visibility = View.VISIBLE
        // Llamada en hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Declaramos la url
                val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/$method?sign=${horoscopo.id}&day=TODAY")
                val con = url.openConnection() as HttpsURLConnection
                con.requestMethod = "GET"
                val responseCode = con.responseCode
                Log.i("HTTP", "Response Code :: $responseCode")

                // Preguntamos si hubo error o no
                if (responseCode == HttpsURLConnection.HTTP_OK) { // Ha ido bien
                    // Metemos el cuerpo de la respuesta en un BurfferedReader
                    val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))
                    var inputLine: String?
                    val response = StringBuffer()
                    while (bufferedReader.readLine().also { inputLine = it } != null) {
                        response.append(inputLine)
                    }
                    bufferedReader.close()

                    // Parsear JSON
                    val json = JSONObject(response.toString())
                    val result =  json.getJSONObject("data").getString("horoscope_data")

                    // Ejecutamos en el hilo principal
                    /*CoroutineScope(Dispatchers.Main).launch {

                    }*/
                    runOnUiThread {
                        dailyHoroscopeTextView.text = result
                        progressIndicator.visibility = View.GONE
                    }

                } else { // Hubo un error
                    Log.w("HTTP", "Response :: Hubo un error")
                }
            } catch (e: Exception) {
                Log.e("HTTP", "Response Error :: ${e.stackTraceToString()}")
            }
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

