package com.artamonov.currencyconverter.main.di

import androidx.lifecycle.ViewModel
import com.artamonov.currencyconverter.main.presenter.RatesViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    @ViewModelKey(RatesViewModel::class)
    internal abstract fun provideRateActivityViewModel(viewModel: RatesViewModel): ViewModel
}
