package com.artamonov.currencyconverter.main.di

import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.interactor.RatesInteractorImpl
import com.artamonov.currencyconverter.main.presenter.RatesPresenter
import com.artamonov.currencyconverter.main.presenter.RatesPresenterImpl
import com.artamonov.currencyconverter.main.view.RatesView
import dagger.Module
import dagger.Provides

@Module
class RateActivityModule {

//    @Provides
//    internal fun providePostMapper(mapper: PostMapperImpl): PostMapper = mapper
//
//    @Provides
//    internal fun providePostCreateMapper(mapper: PostCreateMapperImpl): PostCreateMapper = mapper

    @Provides
    internal fun provideRatesInteractor(interactor: RatesInteractorImpl): RatesInteractor = interactor

    @Provides
    internal fun provideRatesPresenter(presenter: RatesPresenterImpl<RatesView, RatesInteractor>):
            RatesPresenter<RatesView, RatesInteractor> = presenter

}

