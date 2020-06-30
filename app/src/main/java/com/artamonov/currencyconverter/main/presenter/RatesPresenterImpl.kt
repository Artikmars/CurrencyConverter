package com.artamonov.currencyconverter.main.presenter

import android.view.View
import com.artamonov.currencyconverter.main.base.BasePresenter
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.mapper.RatesMapper
import com.artamonov.currencyconverter.main.networking.models.Rate
import com.artamonov.currencyconverter.main.utils.SchedulerProvider
import com.artamonov.currencyconverter.main.view.RatesView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RatesPresenterImpl<V : RatesView, I : RatesInteractor> @Inject internal
constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val ratesMapper: RatesMapper
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), RatesPresenter<V, I> {

    private var currencyRates: List<Rate>? = null

    override fun getCurrencyList(baseCurrency: String?) {
        baseCurrency?.let {
            compositeDisposable.add(interactor!!.getCurrencyList(baseCurrency)
                .map(ratesMapper::map)
                .subscribeOn(schedulerProvider.getIOThreadScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
                .subscribe(
                        { rates ->
                            currencyRates = rates
                            getView()?.showCurrencies() }, { e ->
                            println(e)
                        })
                )
        }
        }

    override fun onAttach(view: View) {
    }
}
