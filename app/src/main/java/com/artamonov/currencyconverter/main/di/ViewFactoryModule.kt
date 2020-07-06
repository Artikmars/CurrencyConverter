package com.artamonov.currencyconverter.main.di

import androidx.lifecycle.ViewModelProvider
import com.artamonov.currencyconverter.main.presenter.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewFactoryModule {

    @Binds
    internal abstract fun provideModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}