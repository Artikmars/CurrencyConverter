package com.artamonov.currencyconverter.main.networking.api;

import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("latest")
    Single<CurrencyRateJson> getCurrencyList(@Query("base") String currency);
}
