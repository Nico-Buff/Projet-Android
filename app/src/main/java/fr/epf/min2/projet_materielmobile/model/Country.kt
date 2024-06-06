package fr.epf.min2.projet_materielmobile.model

import android.os.Parcelable


data class Country(
    val name: Name,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val population: Int,
    val flag: String
)


data class Name(
    val common: String
)