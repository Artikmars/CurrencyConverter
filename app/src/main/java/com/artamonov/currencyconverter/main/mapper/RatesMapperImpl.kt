package com.artamonov.currencyconverter.main.mapper

import com.artamonov.currencyconverter.main.networking.models.AnnotationCurrencyType
import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import com.artamonov.currencyconverter.main.networking.models.Rate
import java.util.Currency
import javax.inject.Inject

class RatesMapperImpl @Inject internal constructor() : RatesMapper {

    override fun map(rateItemsJson: CurrencyRateJson?): MutableList<Rate>? {
        val resultedRateList = mutableListOf<Rate>()
        val currencyList = AnnotationCurrencyType.getCurrencyList(rateItemsJson?.rates)
        for (currency in currencyList) {
            resultedRateList.add(Rate(currencyCode = currency.currencyCode, currencyLongName =
            Currency.getInstance(currency.currencyCode).displayName, rate = currency.rate))
        }
        return resultedRateList
    }
}