package com.sametsisman.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sametsisman.kotlincountries.R
import com.sametsisman.kotlincountries.databinding.FragmentCountryBinding
import com.sametsisman.kotlincountries.util.downloadFromUrl
import com.sametsisman.kotlincountries.util.placeholderProgressBar
import com.sametsisman.kotlincountries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {
    private lateinit var viewModel : CountryViewModel
    private var countryId = 0
    private lateinit var dataBinding: FragmentCountryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryId = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryId)

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {

                dataBinding.selectedCountry = country

                /*
                country_name.text = country.countryName
                country_capital.text = country.countryCapital
                country_currency.text = country.countryCurrency
                country_language.text = country.countryLanguage
                country_region.text = country.countryRegion
                context?.let {
                    country_image.downloadFromUrl(country.imageurl, placeholderProgressBar(it))

                 */
            }
        })
    }
}