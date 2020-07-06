package com.artamonov.currencyconverter.main.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artamonov.currencyconverter.main.interactor.RatesInteractorImpl
import com.artamonov.currencyconverter.main.mapper.RatesMapperImpl
import com.artamonov.currencyconverter.main.networking.models.AnnotationCurrencyType
import com.artamonov.currencyconverter.main.networking.models.Rate
import com.artamonov.currencyconverter.main.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class RatesViewModel @Inject constructor(
    var interactor: RatesInteractorImpl,
    var ratesMapper: RatesMapperImpl,
    private var compositeDisposable: CompositeDisposable,
    private var schedulerProvider: SchedulerProvider
) : ViewModel() {

    private var currencyRates = mutableListOf<Rate>()
    private var baseCurrency = AnnotationCurrencyType.EUR
    private var baseCurrencyValue: Double = 100.0
    var user: MutableLiveData<MutableList<Rate>> = MutableLiveData()
    var moveBaseItem: MutableLiveData<Int> = MutableLiveData()

    fun getCurrencyList(baseCurrency: String?) {
        compositeDisposable.add(interactor.getCurrencyList(baseCurrency ?: this.baseCurrency)
                .map(ratesMapper::map)
                .subscribeOn(schedulerProvider.getIOThreadScheduler())
                .observeOn(schedulerProvider.getMainThreadScheduler())
                .subscribe(
                        { rates ->
                            currencyRates = if (rates.first().currencyCode != baseCurrency) {
                                moveBaseCurrencyToTheTop(rates) } else { rates }
                            applyCurrencyIndex()
                            user.postValue(currencyRates) },
                    { e ->
                            println(e)
                        })
                )
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
            .forEach { it.rate = BigDecimal(it.rate!! * baseCurrencyValue)
                .setScale(2, RoundingMode.HALF_EVEN).toDouble() }
    }

    fun getCurrency(position: Int): Rate {
        return currencyRates[position]
    }

    fun getCurrencyCount(): Int {
        return currencyRates.size
    }

    fun rateChanged(currency: Rate, rateValue: String?, position: Int) {
        baseCurrency = currency.currencyCode
        rateValue?.let {
            baseCurrencyValue = if (rateValue.isNotEmpty() && rateValue != ".") {
                rateValue.toDouble()
            } else {
                0.0
            }
            moveBaseCurrencyToTheTop(currencyRates)
        }
        // getView()?.moveBaseItem(position)
        moveBaseItem.postValue(position)
    }

    //        viewModelScope.launch {
    //            try {
    //                val rates = interactor.getCurrencyList(baseCurrency!!)
    //                val mappedRates = ratesMapper.map(rates)
    //                currencyRates = if (mappedRates.first().currencyCode != baseCurrency) {
    //                    moveBaseCurrencyToTheTop(mappedRates)
    //                } else {
    //                    mappedRates
    //                }
    //                applyCurrencyIndex()
    //                // getView()?.updateList(currencyRates.size)
    //                user.postValue(currencyRates)
    //            } catch (e: Exception) {
    //                println(e)
    //            }
    //        }
}
