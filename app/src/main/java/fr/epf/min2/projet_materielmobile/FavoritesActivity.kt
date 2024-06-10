package fr.epf.min2.projet_materielmobile

import CountryAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min2.projet_materielmobile.model.CountryViewModel

class FavoritesActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoris)

        val backButton: Button = findViewById(R.id.backButton)

        viewModel = CountryViewModel(application)

        adapter = CountryAdapter(this)
        recyclerView = findViewById(R.id.favoritesRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getFavoriteCountries()

        viewModel.countries.observe(this, { countries ->
            countries?.let {
                adapter.submitList(it)
            }
        })

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}