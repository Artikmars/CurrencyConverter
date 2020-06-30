package com.artamonov.currencyconverter.main.networking.api

import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("latest")
    fun getCurrencyList(@Query("base") currency: String): Single<CurrencyRateJson>
}