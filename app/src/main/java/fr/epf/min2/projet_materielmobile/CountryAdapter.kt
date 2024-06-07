import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.epf.min2.projet_materielmobile.CountryDetailActivity
import fr.epf.min2.projet_materielmobile.R
import fr.epf.min2.projet_materielmobile.model.Country

class CountryAdapter : ListAdapter<Country, CountryAdapter.CountryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flagTextView: TextView = itemView.findViewById(R.id.flagTextView)
        private val countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)

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