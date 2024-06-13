package com.example.horoscopo

import androidx.core.graphics.toColor

class HoroscopeProvider {

    companion object{

        private val horoscopeList: List<Horoscope> = listOf(
            Horoscope("aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries, R.drawable.aries_svgrepo_com, R.color.purple_200),
            Horoscope("taurus", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus, R.drawable.taurus_svgrepo_com, R.color.teal_200),
            Horoscope("gemini", R.string.horoscope_name_gemini, R.string.horoscope_date_gemini, R.drawable.gemini_svgrepo_com, R.color.Amarillo),
            Horoscope("cancer", R.string.horoscope_name_cancer, R.string.horoscope_date_cancer, R.drawable.cancer_svgrepo_com, R.color.Rojo),
            Horoscope("leo", R.string.horoscope_name_leo, R.string.horoscope_date_leo, R.drawable.leo_svgrepo_com, R.color.Celeste),
            Horoscope("virgo", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo, R.drawable.virgo_svgrepo_com, R.color.CelesClar),
            Horoscope("libra", R.string.horoscope_name_libra, R.string.horoscope_date_libra, R.drawable.libra_svgrepo_com, R.color.Azulete),
            Horoscope("scorpio", R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio, R.drawable.scorpio_svgrepo_com, R.color.OtrAzul),
            Horoscope("sagittarius", R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius, R.drawable.sagittarius_svgrepo_com, R.color.Nar1),
            Horoscope("capricorn", R.string.horoscope_name_capricorn, R.string.horoscope_date_capricorn, R.drawable.capricorn_svgrepo_com, R.color.Nar3),
            Horoscope("aquarius", R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius_svgrepo_com, R.color.Mar1),
            Horoscope("pisces", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces, R.drawable.pisces_svgrepo_com, R.color.sea)
        )

        fun findAll() : List<Horoscope> {
            return horoscopeList
        }

        fun findById(id: String) : Horoscope? {
            return horoscopeList.find { it.id == id }
        }


    }

}