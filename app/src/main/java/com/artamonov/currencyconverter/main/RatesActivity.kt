package com.artamonov.currencyconverter.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.adapter.AdapterDataSource
import com.artamonov.currencyconverter.main.adapter.CurrencyRateListAdapter
import com.artamonov.currencyconverter.main.base.BaseActivity
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.networking.models.Rate
import com.artamonov.currencyconverter.main.presenter.RatesPresenter
import com.artamonov.currencyconverter.main.view.RatesView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.currency_rate_item.*
import javax.inject.Inject

class RatesActivity : BaseActivity(), RatesView {

    private var adapter: CurrencyRateListAdapter? = null

    @Inject
    internal lateinit var presenter: RatesPresenter<RatesView, RatesInteractor >

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        presenter.getCurrencyList(null)
        initAdapter()
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    private fun initAdapter() {
        adapter = CurrencyRateListAdapter(
            object : AdapterDataSource<Rate> {
                override fun getCount(): Int {
                    return presenter.getCurrencyCount()
                }
                override fun get(position: Int): Rate {
                    return presenter.getCurrency(position)
                }
            }, object : CurrencyRateListAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                }
            })

        val linearLayoutManager = LinearLayoutManager(this)
        currency_list.layoutManager = linearLayoutManager
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        currency_list.layoutManager = linearLayoutManager
        currency_list.adapter = adapter
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}