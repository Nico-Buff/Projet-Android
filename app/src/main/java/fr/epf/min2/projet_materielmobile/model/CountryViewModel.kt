package fr.epf.min2.projet_materielmobile.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.epf.min2.projet_materielmobile.api.RetrofitInstance
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    val countries = MutableLiveData<List<Country>?>()
    val filteredCountries = MutableLiveData<List<Country>?>()

    init {
        viewModelScope.launch {
            try {
                val countriesResponse = RetrofitInstance.api.getAllCountries()
                countries.value = countriesResponse
                Log.d("CountryViewModel", "List of countries received: $countriesResponse")
            } catch (e: Exception) {
                Log.e("CountryViewModel", "Error fetching countries: ${e.message}")
            }
        }
    }

    fun searchCountries(query: String) {
        val filteredList = countries.value?.filter {
            it.name.common.contains(query, ignoreCase = true) || it.capital.get(0).contains(query, ignoreCase = true)
        }
        countries.value = filteredList
    }
}