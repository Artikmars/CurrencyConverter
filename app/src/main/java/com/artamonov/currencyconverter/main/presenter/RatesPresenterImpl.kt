package com.artamonov.currencyconverter.main.presenter

import android.view.View
import com.artamonov.currencyconverter.main.base.BasePresenter
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.utils.SchedulerProvider
import com.artamonov.currencyconverter.main.view.RatesView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RatesPresenterImpl<V : RatesView, I : RatesInteractor> @Inject internal
constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), RatesPresenter<V, I> {
    override fun getCurrencyList(baseCurrency: String?) {
        baseCurrency?.let {
            compositeDisposable.add(interactor!!.getCurrencyList(baseCurrency)
                    .subscribeOn(schedulerProvider.getIOThreadScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
                    .subscribe(
                        { getView()?.showCurrencies() }, { e ->
                            println(e)
                        })
                )
        }
        }

    override fun onAttach(view: View) {
    }
}
