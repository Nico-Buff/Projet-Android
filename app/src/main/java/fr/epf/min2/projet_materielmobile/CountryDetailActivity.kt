package fr.epf.min2.projet_materielmobile

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CountryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val nameTextView: TextView = findViewById(R.id.name)
        val capitalTextView: TextView = findViewById(R.id.capital)
        val regionTextView: TextView = findViewById(R.id.region)
        val subregionTextView: TextView = findViewById(R.id.subregion)
        val populationTextView: TextView = findViewById(R.id.population)
        val flagImageView: ImageView = findViewById(R.id.flagImageView)

        val backButton: Button = findViewById(R.id.backButton)

        val countryName = intent.getStringExtra("country_name")
        val countryCapital = intent.getStringExtra("country_capital")
        val countryRegion = intent.getStringExtra("country_region")
        val countrySubregion = intent.getStringExtra("country_subregion")
        val countryPopulation = intent.getIntExtra("country_population", 0)
        val countryFlag = intent.getStringExtra("country_flag")

        countryName?.let {
            nameTextView.text = it
        }
        countryCapital?.let {
            capitalTextView.text = it
        }
        countryRegion?.let {
            regionTextView.text = it
        }
        countrySubregion?.let {
            subregionTextView.text = it
        }
        populationTextView.text = countryPopulation.toString()

        countryFlag?.let {
            Glide.with(this)
                .load(it)
                .into(flagImageView)
        }

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}