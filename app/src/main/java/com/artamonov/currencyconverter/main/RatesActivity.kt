package com.artamonov.currencyconverter.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.adapter.AdapterDataSource
import com.artamonov.currencyconverter.main.adapter.CurrencyRateListAdapter
import com.artamonov.currencyconverter.main.base.BaseActivity
import com.artamonov.currencyconverter.main.interactor.RatesInteractor
import com.artamonov.currencyconverter.main.networking.models.Rate
import com.artamonov.currencyconverter.main.presenter.RatesPresenter
import com.artamonov.currencyconverter.main.view.RatesView
import kotlinx.android.synthetic.main.activity_main.*
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

    override fun scrollToTop() {
        currency_list.smoothScrollToPosition(0)
    }

    override fun updateList(itemCount: Int) {
        adapter?.notifyItemRangeChanged(1, itemCount)
    }

    override fun moveBaseItem(positionRemoved: Int) {
        adapter?.notifyItemMoved(positionRemoved, 0)
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
                override fun onItemClick(currency: Rate, rateValue: String?, position: Int) {
                    presenter.rateChanged(currency, rateValue, position)
                }
            })

        val linearLayoutManager = LinearLayoutManager(this)
        currency_list.layoutManager = linearLayoutManager
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        currency_list.layoutManager = linearLayoutManager
        currency_list.adapter = adapter

        adapter?.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                scrollToTop()
            }
        })
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}