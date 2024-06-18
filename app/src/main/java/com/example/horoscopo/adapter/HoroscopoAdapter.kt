package com.example.horoscopo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.utils.SessionManager
import com.example.horoscopo.utils.highlight

class HoroscopeAdapter(private var dataSet: List<Horoscope>, private val onItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // Este método se llama para crear nuevas celdas,
    // y se crear las justas para mostrar en pantalla,
    // Ya que intentará reciclar las que no se vean

    /* Para añadir hoy 18Jun24
    fun updateData(list :List<Horoscope>) {
        this.items = list
        notifyDataSetChanged()
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscope, parent, false)

        return HoroscopeViewHolder(view)
    }

    // Este método simplemente es para decir cuantos elementos queremos mostrar
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Este método se llama cada vez que se va a visualizar una celda,
    // y lo utilizaremos para mostrar los datos de esa celda
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = dataSet[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
            // Esto es lo que estaba al principio, pero luego lo cambiamos
        // por lo de arriba: Log.i("ADAPTER". OnItemClick: "He hecho click en la celda")
        }
    }

    // Este método sirve para actualizar los datos
    fun updateData (newDataSet: List<Horoscope>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

}

// Esta clase se encarga de guardarnos la vista y no tener que inflarla de nuevo
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView
    private val descTextView: TextView
    private val logoImageView: ImageView
    private val favoriteImageView: ImageView

    init {
        nameTextView = view.findViewById(R.id.horoscopeTextView)
        descTextView = view.findViewById(R.id.descTextView)
        logoImageView = view.findViewById(R.id.horoscopeImageView)
        favoriteImageView = view.findViewById(R.id.horoscopeFavoriteImageView)
    }

    fun render(horoscope: Horoscope) {
        nameTextView.setText(horoscope.name)
        logoImageView.setImageResource(horoscope.logo)
        descTextView.setText(horoscope.description)


        //Para mostrar cual es el favorito, osea la imagen corazón encima del signo zodiacal
        val context = itemView.context
        val isFavorite = SessionManager(context).favoriteHoroscope == horoscope.id
        if (isFavorite) {
            favoriteImageView.visibility = View.VISIBLE
        } else {
            favoriteImageView.visibility = View.GONE
        }
        itemView.setBackgroundResource(horoscope.color)
    }

    // Subraya el texto que coincide con la busqueda
    fun highlight(text: String) {
        try {
            val highlighted = nameTextView.text.toString().highlight(text)
            nameTextView.text = highlighted
        } catch (e: Exception) { }
        try {
            val highlighted = descTextView.text.toString().highlight(text)
            descTextView.text = highlighted
        } catch (e: Exception) { }
    }

}

/* import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HoroscopeAdapter(private val dataSet: List<Horoscope>) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // Este método se llama para crear nuevas celdas,
    // y se crear las justas para mostrar en pantalla,
    // Ya que intentará reciclar las que no se vean
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscope, parent, false)

        return HoroscopeViewHolder(view)
    }

    // Este método simplemente es para decir cuantos elementos queremos mostrar
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Este método se llama cada vez que se va a visualizar una celda,
    // y lo utilizaremos para mostrar los datos de esa celda
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = dataSet[position]
        holder.textView.text = horoscope.name

    }

}

// Esta clase se encarga de guardarnos la vista y no tener que inflarla de nuevo
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView

    init {
        textView = view.findViewById(R.id.Horoscope)
    }
}*/