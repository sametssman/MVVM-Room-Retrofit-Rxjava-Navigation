package com.sametsisman.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sametsisman.kotlincountries.R
import com.sametsisman.kotlincountries.model.Country
import com.sametsisman.kotlincountries.util.downloadFromUrl
import com.sametsisman.kotlincountries.util.placeholderProgressBar
import com.sametsisman.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    class CountryHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerview_row,parent,false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.view.country_name.text = countryList[position].countryName
        holder.view.country_region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.image.downloadFromUrl(countryList[position].imageurl, placeholderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}