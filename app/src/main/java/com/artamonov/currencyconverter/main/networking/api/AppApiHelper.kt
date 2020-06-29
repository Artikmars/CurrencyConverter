package com.artamonov.currencyconverter.main.networking.api

import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import io.reactivex.Single
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val api: API) : ApiHelper {

    override fun getCurrency(baseCurrency: String): Single<CurrencyRateJson> {
        return api.getCurrencyList(baseCurrency)
    }

}
