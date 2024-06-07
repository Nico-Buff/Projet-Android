package fr.epf.min2.projet_materielmobile.model

import CountryAdapter
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.epf.min2.projet_materielmobile.api.RetrofitInstance
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    val countries = MutableLiveData<List<Country>?>()
    var filteredList: List<Country> ?= null
    lateinit var adapter: CountryAdapter
    val countriesEx = mutableListOf<Country>()


    /*init {
        countriesEx.add(Country(Name("France"), listOf("Paris"), "Europe", "Western Europe", 67081000, "https://imgs.search.brave.com/Thwh7--nvEFt3p-XRjrIpc-BGknWcwRykwRGtzu2Etg/rs:fit:500:0:0/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvZW4vdGh1bWIv/Yy9jMy9GbGFnX29m/X0ZyYW5jZS5zdmcv/NTEycHgtRmxhZ19v/Zl9GcmFuY2Uuc3Zn/LnBuZw"))
        countriesEx.add(Country(Name("Germany"), listOf("Berlin"), "Europe", "Western Europe", 83149300, "https://www.google.com/imgres?q=drapeau%20allemagne&imgurl=https%3A%2F%2Fimg.freepik.com%2Fvecteurs-premium%2Fdrapeau-allemagne-illustration-vectorielle_514344-272.jpg&imgrefurl=https%3A%2F%2Ffr.freepik.com%2Fphotos-vecteurs-libre%2Fdrapeau-allemagne&docid=OvfCYVm7B2enSM&tbnid=IdjfY70e7lGs9M&vet=12ahUKEwiQx_nOm8mGAxWKTKQEHZkADLIQM3oECBwQAA..i&w=626&h=401&hcb=2&ved=2ahUKEwiQx_nOm8mGAxWKTKQEHZkADLIQM3oECBwQAA"))
        countriesEx.add(Country(Name("Italy"), listOf("Rome"), "Europe", "Southern Europe", 60317000, "https://www.google.com/imgres?q=drapeau%20italy&imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F0%2F03%2FFlag_of_Italy.svg%2F2560px-Flag_of_Italy.svg.png&imgrefurl=https%3A%2F%2Ffr.m.wikipedia.org%2Fwiki%2FFichier%3AFlag_of_Italy.svg&docid=lyqXsc9D1OgaMM&tbnid=HHcg58yUf36OeM&vet=12ahUKEwi3vavvnMmGAxUOBfsDHbv0BwEQM3oECCAQAA..i&w=2560&h=1707&hcb=2&ved=2ahUKEwi3vavvnMmGAxUOBfsDHbv0BwEQM3oECCAQAA"))
        countriesEx.add(Country(Name("Spain"), listOf("Madrid"), "Europe", "Southern Europe", 46733038, "https://example.com/flags/spain.png"))
        countriesEx.add(Country(Name("United Kingdom"), listOf("London"), "Europe", "Northern Europe", 66650000, "https://example.com/flags/uk.png"))
        countriesEx.add(Country(Name("Portugal"), listOf("Lisbon"), "Europe", "Southern Europe", 10276617, "https://example.com/flags/portugal.png"))
        countriesEx.add(Country(Name("Russia"), listOf("Moscow"), "Europe", "Eastern Europe", 146599183, "https://example.com/flags/russia.png"))
        countriesEx.add(Country(Name("China"), listOf("Beijing"), "Asia", "Eastern Asia", 1393000000, "https://example.com/flags/china.png"))
        countriesEx.add(Country(Name("Japan"), listOf("Tokyo"), "Asia", "Eastern Asia", 126500000, "https://example.com/flags/japan.png"))
        countriesEx.add(Country(Name("South Korea"), listOf("Seoul"), "Asia", "Eastern Asia", 51709098, "https://example.com/flags/south_korea.png"))
        countriesEx.add(Country(Name("United States"), listOf("Washington D.C."), "Americas", "North America", 331449281, "https://example.com/flags/usa.png"))
        countriesEx.add(Country(Name("Canada"), listOf("Ottawa"), "Americas", "North America", 37742154, "https://example.com/flags/canada.png"))
        countriesEx.add(Country(Name("Brazil"), listOf("Brasília"), "Americas", "South America", 211049527, "https://example.com/flags/brazil.png"))
        countriesEx.add(Country(Name("Australia"), listOf("Canberra"), "Oceania", "Australia and New Zealand", 25649909, "https://example.com/flags/australia.png"))
        countriesEx.add(Country(Name("New Zealand"), listOf("Wellington"), "Oceania", "Australia and New Zealand", 4917000, "https://example.com/flags/new_zealand.png"))

        init()
    }*/

    init {
        adapter = CountryAdapter()
        viewModelScope.launch {
            var success = false
            while (!success) {
                try {
                    val countriesResponse = RetrofitInstance.api.getAllCountries()
                    countries.value = countriesResponse
                    success = true
                } catch (e: Exception) {
                    Log.e("CountryViewModel", "Error fetching countries: ${e.message}")
                    kotlinx.coroutines.delay(2000L)
                }
            }
        }
    }

    fun searchCountries(query: String) {
        filteredList = countries.value?.filter {
            it.name.common.contains(query, ignoreCase = true) || (it.capital?.getOrNull(0)
                ?.contains(query, ignoreCase = true) == true)
        } ?: emptyList()
        countries.value = filteredList
    }
}