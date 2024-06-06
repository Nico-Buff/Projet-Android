package fr.epf.min2.projet_materielmobile.api

import fr.epf.min2.projet_materielmobile.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {
    @GET("v3.1/name/{name}")
    suspend fun getAllCountries(): List<Country>

    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): List<Country>
}