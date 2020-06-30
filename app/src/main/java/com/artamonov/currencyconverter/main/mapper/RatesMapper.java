package com.artamonov.currencyconverter.main.mapper;

import com.artamonov.currencyconverter.main.networking.models.CurrencyRateJson;
import com.artamonov.currencyconverter.main.networking.models.Rate;
import java.util.List;

public interface RatesMapper {

    List<Rate> map(final CurrencyRateJson rateItemsJson);
}
