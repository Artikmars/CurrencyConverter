package com.artamonov.currencyconverter.main.interactor

import com.artamonov.currencyconverter.main.base.Interactor
import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import io.reactivex.Single

interface RatesInteractor : Interactor {
    fun getCurrencyList(baseCurrency: String): Single<CurrencyRateJson>
}