package com.artamonov.currencyconverter.main.di

import com.artamonov.currencyconverter.main.RatesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(RateActivityModule::class)])
    abstract fun bindRateActivity(): RatesActivity
}