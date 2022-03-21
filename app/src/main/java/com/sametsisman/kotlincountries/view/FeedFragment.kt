package com.sametsisman.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sametsisman.kotlincountries.R
import com.sametsisman.kotlincountries.adapter.CountryAdapter
import com.sametsisman.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            errorText.visibility = View.GONE
            countryLoading.visibility = View.INVISIBLE
            viewModel.refreshFromAPI()

            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->

            countries?.let {
                recyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it) {
                    errorText.visibility = View.VISIBLE
                } else {
                    errorText.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    countryLoading.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorText.visibility = View.GONE
                } else {
                    countryLoading.visibility = View.GONE
                }
            }
        })
    }

}