package com.artamonov.currencyconverter.main.presenter

import android.view.View
import com.artamonov.currencyconverter.main.base.Presenter
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.view.RatesView

interface RatesPresenter <V : RatesView, I : RatesInteractor> : Presenter<V, I> {

    fun getCurrencyList(baseCurrency: String?)

    fun onAttach(view: View)

}