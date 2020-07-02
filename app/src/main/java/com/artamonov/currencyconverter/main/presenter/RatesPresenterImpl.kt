package com.artamonov.currencyconverter.main.presenter

import android.view.View
import com.artamonov.currencyconverter.main.base.BasePresenter
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.mapper.RatesMapper
import com.artamonov.currencyconverter.main.networking.models.AnnotationCurrencyType
import com.artamonov.currencyconverter.main.networking.models.Rate
import com.artamonov.currencyconverter.main.utils.SchedulerProvider
import com.artamonov.currencyconverter.main.view.RatesView
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
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

    private var currencyRates = mutableListOf<Rate>()
    private var baseCurrency = AnnotationCurrencyType.EUR
    private var baseCurrencyValue: Double = 100.0

    override fun getCurrencyList(baseCurrency: String?) {
            compositeDisposable.add(interactor!!.getCurrencyList(baseCurrency ?: this.baseCurrency)
                .repeatWhen { Flowable.timer(1, TimeUnit.SECONDS).repeat() }
                .map(ratesMapper::map)
                .subscribeOn(schedulerProvider.getIOThreadScheduler())
                .observeOn(schedulerProvider.getMainThreadScheduler())
                .subscribe(
                        { rates ->
                            currencyRates = if (rates.first().currencyCode != baseCurrency) {
                                moveBaseCurrencyToTheTop(rates) } else { rates }
                            applyCurrencyIndex()
                            getView()?.updateList(currencyRates.size) },
                    { e ->
                            println(e)
                        })
                )
        }

    override fun onAttach(view: View) {
    }

    private fun moveBaseCurrencyToTheTop(rates: MutableList<Rate>): MutableList<Rate> {
        val currency = rates.find { it.currencyCode == baseCurrency }
        currency?.rate = baseCurrencyValue
        currency?.let {
            rates.removeAt(rates.indexOf(currency))
            rates.add(0, currency)
            return rates
        }
        return rates
    }

    private fun applyCurrencyIndex() {
        currencyRates
            .filterNot { it.currencyCode == baseCurrency }
            .forEach { it.rate = it.rate!! * baseCurrencyValue }
    }

    override fun getCurrency(position: Int): Rate {
        return currencyRates[position]
    }

    override fun getCurrencyCount(): Int {
        return currencyRates.size
    }

    override fun rateChanged(currency: Rate, rateValue: String?, position: Int) {
        baseCurrency = currency.currencyCode
        rateValue?.let {
            if (rateValue.isNotEmpty()) { baseCurrencyValue = rateValue.toDouble() }
            moveBaseCurrencyToTheTop(currencyRates)
        }
        getView()?.moveBaseItem(position)
    }
}
