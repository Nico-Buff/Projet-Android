package fr.epf.min2.projet_materielmobile.model

import androidx.core.view.ContentInfoCompat

data class Country(
    val name: names,
    val capital: List<String>,
    val flag: String
)

data class names(
    val common : String
)