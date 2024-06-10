package com.example.horoscopo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_taurus, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_gemini, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_cancer, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_leo, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_virgo, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_libra, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_scorpio, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_sagittarius, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_capricorn, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_aquarius, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com),
        Horoscope("aries", R.string.horoscope_name_pisces, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com)
    )

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopeAdapter(horoscopeList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
/*import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", "Aries", 0),
        Horoscope("tauro", "Tauro", 0),
        Horoscope("geminis", "Geminis", 0),
        Horoscope("cancer", "Cancer", 0),
        Horoscope("leo", "Leo", 0),
        Horoscope("virgo", "Virgo", 0),
        Horoscope("leo", "Leo", 0),
        Horoscope("escorpio", "Escorpio", 0),
        Horoscope("sagitario", "Sagitario", 0),
        Horoscope("capricornio", "Capricornio", 0),
        Horoscope("acuario", "Acuario", 0),
        Horoscope("pisis", "Pisis", 0)
    )

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopeAdapter(horoscopeList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }
}
*/