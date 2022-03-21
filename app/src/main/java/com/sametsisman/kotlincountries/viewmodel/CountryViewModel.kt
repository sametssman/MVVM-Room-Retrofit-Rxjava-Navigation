package com.sametsisman.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametsisman.kotlincountries.model.Country

class CountryViewModel : ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
    }
}