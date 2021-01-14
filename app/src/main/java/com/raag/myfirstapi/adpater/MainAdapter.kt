package com.raag.myfirstapi.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raag.myfirstapi.R
import com.raag.myfirstapi.data.Countries
import com.raag.myfirstapi.databinding.ItemCountryBinding
import com.squareup.picasso.Picasso


class MainAdapter(private val context: Context, private val dataList: List<Countries>):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_country, parent, false
        )
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size


    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountryBinding.bind(view)
        fun bind(countries: Countries) = with(binding){
            this.capital.text = countries.capital
            this.name.text = countries.name
            this.subRegion.text = countries.subregion
            this.code.text = countries.alpha3Code
            this.poblacion.text = countries.population.toString()
            this.area.text = countries.area.toString()
            Picasso.get().load(countries.flag).into(binding.imgFlag)
        }
    }
}