package fr.epf.min2.projet_materielmobile.model

import CountryAdapter
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.epf.min2.projet_materielmobile.FavoriteRepository
import fr.epf.min2.projet_materielmobile.api.RetrofitInstance
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    val countries = MutableLiveData<List<Country>?>()
    var filteredList: List<Country> ?= null
    lateinit var adapter: CountryAdapter
    val notFilteredList = mutableListOf<Country>()
    private val favoriteRepository = FavoriteRepository(application)


    init {
        adapter = CountryAdapter(application)
        viewModelScope.launch {
            var success = false
            while (!success) {
                try {
                    val countriesResponse = RetrofitInstance.api.getAllCountries()
                    countries.value = countriesResponse
                    notFilteredList.addAll(countriesResponse)
                    success = true
                } catch (e: Exception) {
                    Log.e("CountryViewModel", "Error fetching countries: ${e.message}")
                    kotlinx.coroutines.delay(2000L)
                }
            }
        }
    }

    fun searchCountries(query: String) {
        countries.value = notFilteredList
        filteredList = countries.value?.filter {
            it.name.common.contains(query, ignoreCase = true) || (it.capital?.getOrNull(0)
                ?.contains(query, ignoreCase = true) == true)
        } ?: emptyList()
        countries.value = filteredList
    }

    fun getFavoriteCountries() {
        countries.value = favoriteRepository.getFavorites()
    }
}