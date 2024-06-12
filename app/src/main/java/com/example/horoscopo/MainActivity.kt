package com.example.horoscopo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {




    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopeAdapter(horoscopeList) { position ->
            navigateToDetail(horoscopeList[position])
        /* Con ESTO, he llamado a la "LISTA"*/
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    fun navigateToDetail(horoscope: Horoscope) {
        val intent: Intent = Intent(this, Activity_Detail::class.java)
        intent.putExtra("HOROSCOPE_ID", horoscope.id)
        intent.putExtra("HOROSCOPE_NAME", horoscope.name)
        intent.putExtra("HOROSCOPE_LOGO", horoscope.logo)
        startActivity(intent)
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