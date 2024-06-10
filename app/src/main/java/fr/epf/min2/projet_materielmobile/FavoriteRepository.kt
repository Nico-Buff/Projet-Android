package fr.epf.min2.projet_materielmobile

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.epf.min2.projet_materielmobile.model.Country

class FavoriteRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getFavorites(): List<Country> {
        val json = sharedPreferences.getString("favorite_countries", null)
        return if (json != null) {
            val type = object : TypeToken<List<Country>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun addFavorite(country: Country) {
        val favorites = getFavorites().toMutableList()
        if (!favorites.contains(country)) {
            favorites.add(country)
            saveFavorites(favorites)
        }
    }

    fun removeFavorite(country: Country) {
        val favorites = getFavorites().toMutableList()
        if (favorites.contains(country)) {
            favorites.remove(country)
            saveFavorites(favorites)
        }
    }

    private fun saveFavorites(favorites: List<Country>) {
        val json = gson.toJson(favorites)
        sharedPreferences.edit().putString("favorite_countries", json).apply()
    }
}
