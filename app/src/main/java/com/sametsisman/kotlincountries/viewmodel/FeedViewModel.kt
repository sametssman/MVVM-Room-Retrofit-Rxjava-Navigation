package com.sametsisman.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametsisman.kotlincountries.model.Country
import com.sametsisman.kotlincountries.service.CountryAPIService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryAPIService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryLoading.value = false
                        countryError.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }
}