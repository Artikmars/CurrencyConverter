package com.artamonov.currencyconverter.main

import android.os.Bundle
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.base.BaseActivity
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.presenter.RatesPresenter
import com.artamonov.currencyconverter.main.view.RatesView
import javax.inject.Inject

class RatesActivity : BaseActivity(), RatesView {

    @Inject
    internal lateinit var presenter: RatesPresenter<RatesView, RatesInteractor >

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        Thread.sleep(5000)

        presenter.getCurrencyList("CNY")
    }

    override fun showCurrencies() {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}