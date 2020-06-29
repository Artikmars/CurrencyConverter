package com.artamonov.currencyconverter.main.networking.models

import androidx.annotation.StringDef

object AnnotationCurrencyType {
    const val AUD = "AUD"
    const val BGN = "BGN"
    const val BRL = "BRL"
    const val CZK = "CZK"
    const val DKK = "DKK"
    const val EUR = "EUR"
    const val HKD = "HKD"
    const val HRK = "HRK"
    const val HUF = "HUF"
    const val IDR = "IDR"
    const val ILS = "ILS"
    const val INR = "INR"
    const val ISK = "ISK"
    const val JPY = "JPY"
    const val KRW = "KRW"
    const val MXN = "MXN"
    const val MYR = "MYR"
    const val NOK = "NOK"
    const val NZD = "NZD"
    const val PHP = "PHP"
    const val PLN = "PLN"
    const val RON = "RON"
    const val RUB = "RUB"
    const val SEK = "SEK"
    const val SGD = "SGD"
    const val THB = "THB"
    const val USD = "USD"
    const val ZAR = "ZAR"

    @StringDef(
        AUD, BGN, BRL, CZK, DKK, EUR, HKD, HRK, HUF, IDR, ILS, INR, ISK, JPY, KRW, MXN, MYR, NOK,
        NZD, PHP, PLN, RON, RUB, SEK, SGD, THB, USD, ZAR
    )
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class AnnotationCurrencyType
}