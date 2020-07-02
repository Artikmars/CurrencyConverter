package com.artamonov.currencyconverter.main.networking.models

class Rate(val currencyCode: String, val currencyLongName: String? = null, var rate: Double?, var iconResourceId: Int? = null)