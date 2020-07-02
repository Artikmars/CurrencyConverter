package com.artamonov.currencyconverter.main.mapper

import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import com.artamonov.currencyconverter.main.networking.models.Rate

interface RatesMapper {
    fun map(rateItemsJson: CurrencyRateJson?): MutableList<Rate>
}