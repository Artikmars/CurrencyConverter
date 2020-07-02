package com.artamonov.currencyconverter.main.networking.models

import androidx.annotation.StringDef
import com.artamonov.currencyconverter.R

object AnnotationCurrencyType {
    const val AUD = "AUD"
    const val BGN = "BGN"
    const val BRL = "BRL"
    const val CAD = "CAD"
    const val CHF = "CHF"
    const val CZK = "CZK"
    const val DKK = "DKK"
    const val EUR = "EUR"
    const val GBP = "GBP"
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

    fun getCurrencyList(rates: Rates?): MutableList<Rate> {
        return mutableListOf(Rate(currencyCode = AUD, rate = rates?.AUD),
            Rate(currencyCode = BGN, rate = rates?.BGN), Rate(currencyCode = BRL, rate = rates?.BRL),
            Rate(currencyCode = CAD, rate = rates?.CAD), Rate(currencyCode = CHF, rate = rates?.CHF),
            Rate(currencyCode = CZK, rate = rates?.CZK), Rate(currencyCode = DKK, rate = rates?.DKK),
            Rate(currencyCode = EUR, rate = rates?.EUR), Rate(currencyCode = GBP, rate = rates?.GBP),
            Rate(currencyCode = HKD, rate = rates?.HKD), Rate(currencyCode = HRK, rate = rates?.HRK),
            Rate(currencyCode = HUF, rate = rates?.HUF), Rate(currencyCode = IDR, rate = rates?.IDR),
            Rate(currencyCode = ILS, rate = rates?.ILS), Rate(currencyCode = INR, rate = rates?.INR),
            Rate(currencyCode = ISK, rate = rates?.ISK), Rate(currencyCode = JPY, rate = rates?.JPY),
            Rate(currencyCode = KRW, rate = rates?.KRW), Rate(currencyCode = MXN, rate = rates?.MXN),
            Rate(currencyCode = MYR, rate = rates?.MYR), Rate(currencyCode = NOK, rate = rates?.NOK),
            Rate(currencyCode = NZD, rate = rates?.NZD), Rate(currencyCode = PHP, rate = rates?.PHP),
            Rate(currencyCode = PLN, rate = rates?.PLN), Rate(currencyCode = RON, rate = rates?.RON),
            Rate(currencyCode = RUB, rate = rates?.RUB), Rate(currencyCode = SEK, rate = rates?.SEK),
            Rate(currencyCode = SGD, rate = rates?.SGD), Rate(currencyCode = THB, rate = rates?.THB),
            Rate(currencyCode = USD, rate = rates?.USD), Rate(currencyCode = ZAR, rate = rates?.ZAR))
    }

    fun setIcons(rates: List<Rate>): MutableList<Rate> {
        rates.forEach {
            when (it.currencyCode) {
                AUD -> it.iconResourceId = R.drawable.au
                BGN -> it.iconResourceId = R.drawable.bg
                BRL -> it.iconResourceId = R.drawable.br
                CAD -> it.iconResourceId = R.drawable.ca
                CHF -> it.iconResourceId = R.drawable.sw
                CZK -> it.iconResourceId = R.drawable.cz
                DKK -> it.iconResourceId = R.drawable.dk
                EUR -> it.iconResourceId = R.drawable.eu
                GBP -> it.iconResourceId = R.drawable.gb
                HKD -> it.iconResourceId = R.drawable.hk
                HRK -> it.iconResourceId = R.drawable.cr
                HUF -> it.iconResourceId = R.drawable.hu
                IDR -> it.iconResourceId = R.drawable.id
                ILS -> it.iconResourceId = R.drawable.il
                INR -> it.iconResourceId = R.drawable.id
                ISK -> it.iconResourceId = R.drawable.`is`
                JPY -> it.iconResourceId = R.drawable.jp
                KRW -> it.iconResourceId = R.drawable.kr
                MXN -> it.iconResourceId = R.drawable.mx
                MYR -> it.iconResourceId = R.drawable.my
                NOK -> it.iconResourceId = R.drawable.no
                NZD -> it.iconResourceId = R.drawable.nz
                PHP -> it.iconResourceId = R.drawable.ph
                PLN -> it.iconResourceId = R.drawable.pl
                RON -> it.iconResourceId = R.drawable.ro
                RUB -> it.iconResourceId = R.drawable.ru
                SEK -> it.iconResourceId = R.drawable.se
                SGD -> it.iconResourceId = R.drawable.sg
                THB -> it.iconResourceId = R.drawable.th
                USD -> it.iconResourceId = R.drawable.us
                ZAR -> it.iconResourceId = R.drawable.sa
            }
        }
        return rates.toMutableList()
    }

    @StringDef(
        AUD, BGN, BRL, CAD, CHF, CZK, DKK, EUR, GBP, HKD, HRK, HUF, IDR, ILS, INR, ISK, JPY, KRW, MXN, MYR, NOK,
        NZD, PHP, PLN, RON, RUB, SEK, SGD, THB, USD, ZAR
    )
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class AnnotationCurrencyType
}