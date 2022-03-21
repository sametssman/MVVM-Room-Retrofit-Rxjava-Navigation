package com.sametsisman.kotlincountries.service

import com.sametsisman.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val Base_Url = "https://raw.githubusercontent.com/"

    private val api =
        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountryAPI::class.java)

    fun getCountries() : Single<List<Country>>{
        return api.getCountries()
    }
}