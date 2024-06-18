package com.example.horoscopo.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.adapter.HoroscopeAdapter
import com.example.horoscopo.data.HoroscopeProvider
import com.example.horoscopo.R

class MainActivity : AppCompatActivity() {

    lateinit var horoscopeList: List<Horoscope>

    lateinit var recyclerView: RecyclerView

    lateinit var adapter: HoroscopeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        horoscopeList = HoroscopeProvider.findAll()

        adapter = HoroscopeAdapter(horoscopeList) { position ->
            navigateToDetail(horoscopeList[position])
        /* Con ESTA FUNCION LAMBDA, he llamado a la "LISTA" utiliza "navigate punto33*/
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(horoscopeList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)

        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchView = searchViewItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    horoscopeList = HoroscopeProvider.findAll()
                        .filter { getString(it.name).contains(newText, true) }
                    adapter.updateData(horoscopeList)
                }
                return true
            }
        })

        return true
    }

    fun navigateToDetail(horoscope: Horoscope) {
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("HOROSCOPE_ID", horoscope.id)
        //intent.putExtra("HOROSCOPE_NAME", horoscope.name)
        //intent.putExtra("HOROSCOPE_LOGO", horoscope.logo)
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