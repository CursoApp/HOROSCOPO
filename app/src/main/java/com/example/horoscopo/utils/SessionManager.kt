package com.example.horoscopo.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    companion object {
        const val FAVORITE_HOROSCOPE = "FAVORITE_HOROSCOPE"
    }

    private var sharedPref: SharedPreferences

    var favoriteHoroscope:String?
        get() = getFavoriteHoroscopeValue()
        set(value) = setFavoriteHoroscopeValue(value!!)



    init {
        sharedPref = context.getSharedPreferences("horoscope_session", Context.MODE_PRIVATE)
    }

    fun setFavoriteHoroscopeValue(id: String) {
        val editor = sharedPref.edit()
        editor.putString(FAVORITE_HOROSCOPE, id)
        editor.apply()
    }

    fun getFavoriteHoroscopeValue() : String? {
        return sharedPref.getString(FAVORITE_HOROSCOPE, null)
    }
}