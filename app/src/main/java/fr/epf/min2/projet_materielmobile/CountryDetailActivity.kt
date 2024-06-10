package fr.epf.min2.projet_materielmobile

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import fr.epf.min2.projet_materielmobile.model.Country
import fr.epf.min2.projet_materielmobile.model.Name

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var country: Country
    private lateinit var favoriteRepository: FavoriteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val nameTextView: TextView = findViewById(R.id.name)
        val capitalTextView: TextView = findViewById(R.id.capital)
        val regionTextView: TextView = findViewById(R.id.region)
        val subregionTextView: TextView = findViewById(R.id.subregion)
        val populationTextView: TextView = findViewById(R.id.population)
        val flagImageView: TextView = findViewById(R.id.flagTextView)

        val favoriteButton: Button = findViewById(R.id.favoriteButton)
        val backButton: Button = findViewById(R.id.backButton)

        val countryName = intent.getStringExtra("country_name")
        val countryCapital = intent.getStringExtra("country_capital")
        val countryRegion = intent.getStringExtra("country_region")
        val countrySubregion = intent.getStringExtra("country_subregion")
        val countryPopulation = intent.getIntExtra("country_population", 0)
        val countryFlag = intent.getStringExtra("country_flag")

        favoriteRepository = FavoriteRepository(this)

        country = Country(Name(countryName ?: ""), listOf(countryCapital ?: ""), countryRegion ?: "", countrySubregion ?: "", countryPopulation, countryFlag ?: "")
        nameTextView.text = country.name.common
        capitalTextView.text = country.capital.joinToString()
        regionTextView.text = country.region
        subregionTextView.text = country.subregion
        populationTextView.text = country.population.toString()
        flagImageView.text = country.flag



        updateFavoriteButton()

        favoriteButton.setOnClickListener {
            if (favoriteRepository.getFavorites().contains(country)) {
                favoriteRepository.removeFavorite(country)
            } else {
                favoriteRepository.addFavorite(country)
            }
            updateFavoriteButton()
        }

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun updateFavoriteButton() {
        val favoriteButton: Button = findViewById(R.id.favoriteButton)
        if (favoriteRepository.getFavorites().contains(country)) {
            favoriteButton.text = getString(R.string.remove_from_favorites)
        } else {
            favoriteButton.text = getString(R.string.add_to_favorites)
        }
    }
}