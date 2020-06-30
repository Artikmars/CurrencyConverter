package com.artamonov.currencyconverter.main.di

import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.interactor.RatesInteractorImpl
import com.artamonov.currencyconverter.main.mapper.RatesMapper
import com.artamonov.currencyconverter.main.mapper.RatesMapperImpl
import com.artamonov.currencyconverter.main.presenter.RatesPresenter
import com.artamonov.currencyconverter.main.presenter.RatesPresenterImpl
import com.artamonov.currencyconverter.main.view.RatesView
import dagger.Module
import dagger.Provides

@Module
class RateActivityModule {

    @Provides
    internal fun provideRatesMapper(mapper: RatesMapperImpl): RatesMapper = mapper

    @Provides
    internal fun provideRatesInteractor(interactor: RatesInteractorImpl): RatesInteractor = interactor

    @Provides
    internal fun provideRatesPresenter(presenter: RatesPresenterImpl<RatesView, RatesInteractor>):
            RatesPresenter<RatesView, RatesInteractor> = presenter
}
