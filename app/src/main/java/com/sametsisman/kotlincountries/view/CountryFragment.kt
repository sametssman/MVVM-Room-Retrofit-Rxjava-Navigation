package com.sametsisman.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sametsisman.kotlincountries.R
import com.sametsisman.kotlincountries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {
    private lateinit var viewModel : CountryViewModel
    private var countryId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        /*arguments?.let {
            countryId = CountryFragmentArgs.fromBundle(it).countryUuid
        }*/

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {
                country_name.text = country.countryName
                country_capital.text = country.countryCapital
                country_currency.text = country.countryCurrency
                country_language.text = country.countryLanguage
                country_region.text = country.countryRegion

            }
        })
    }
}