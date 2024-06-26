    package fr.epf.min2.projet_materielmobile

    import CountryAdapter
    import android.content.Intent
    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import android.widget.SearchView
    import androidx.appcompat.app.AppCompatActivity
    import androidx.lifecycle.ViewModelProvider
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import fr.epf.min2.projet_materielmobile.model.CountryViewModel

    class MainActivity : AppCompatActivity()  {

        private lateinit var viewModel: CountryViewModel
        private lateinit var adapter: CountryAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            val searchView = findViewById<SearchView>(R.id.searchView)

            adapter = CountryAdapter(this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

            viewModel.countries.observe(this, { countries ->
                adapter.submitList(countries)
            })

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.searchCountries(query) }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { viewModel.searchCountries(newText) }
                    Log.e("MainActivity", "newText: $newText")
                    return false
                }
            })

            findViewById<View>(R.id.button_favorites).setOnClickListener {
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
            }
        }

    }