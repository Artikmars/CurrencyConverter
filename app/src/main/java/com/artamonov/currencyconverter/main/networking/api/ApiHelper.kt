package com.artamonov.currencyconverter.main.networking.api

import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import io.reactivex.Single

interface ApiHelper {
    fun getCurrency(baseCurrency: String): Single<CurrencyRateJson>
}
