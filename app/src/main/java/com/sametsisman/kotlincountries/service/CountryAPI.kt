package com.sametsisman.kotlincountries.service

import com.sametsisman.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries() : Single<List<Country>>
}