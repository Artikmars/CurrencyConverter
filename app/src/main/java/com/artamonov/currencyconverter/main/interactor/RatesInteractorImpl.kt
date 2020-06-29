package com.artamonov.currencyconverter.main.interactor

import com.artamonov.currencyconverter.main.networking.api.ApiHelper
import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import io.reactivex.Single
import javax.inject.Inject

class RatesInteractorImpl @Inject
constructor(
    private val apiHelper: ApiHelper
) : RatesInteractor {

    override fun getCurrencyList(baseCurrency: String): Single<CurrencyRateJson> {
        return apiHelper.getCurrency(baseCurrency)
    }
}