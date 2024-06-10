import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.epf.min2.projet_materielmobile.CountryDetailActivity
import fr.epf.min2.projet_materielmobile.FavoriteRepository
import fr.epf.min2.projet_materielmobile.R
import fr.epf.min2.projet_materielmobile.model.Country


class CountryAdapter (private val context: Context) : ListAdapter<Country, CountryAdapter.CountryViewHolder>(DiffCallback()) {

    private val favoriteRepository = FavoriteRepository(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view, favoriteRepository)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CountryViewHolder(itemView: View, private val favoriteRepository: FavoriteRepository) : RecyclerView.ViewHolder(itemView) {
        private val flagTextView: TextView = itemView.findViewById(R.id.flagTextView)
        private val countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)
        private val favoriteButton: ImageButton = itemView.findViewById(R.id.favoriteButton)

        fun bind(country: Country) {
            countryNameTextView.text = country.name.common
            flagTextView.text = country.flag


            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, CountryDetailActivity::class.java).apply {
                    putExtra("country_name", country.name.common)
                    putExtra("country_capital", country.capital[0])
                    putExtra("country_region", country.region)
                    putExtra("country_subregion", country.subregion)

                    putExtra("country_population", country.population)
                    putExtra("country_flag", country.flag)
                }
                context.startActivity(intent)
            }

            if (favoriteRepository.getFavorites().contains(country)) {
                favoriteButton.setImageResource(R.drawable.ic_star_filled)
            } else {
                favoriteButton.setImageResource(R.drawable.ic_star_border)
            }

            favoriteButton.setOnClickListener {
                if (favoriteRepository.getFavorites().contains(country)) {
                    favoriteRepository.removeFavorite(country)
                    favoriteButton.setImageResource(R.drawable.ic_star_border)
                } else {
                    favoriteRepository.addFavorite(country)
                    favoriteButton.setImageResource(R.drawable.ic_star_filled)
                }
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name.common == newItem.name.common
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}