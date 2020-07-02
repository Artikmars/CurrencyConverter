package com.artamonov.currencyconverter.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private var handler: Handler? = null

    @Inject
    internal lateinit var presenter: RatesPresenter<RatesView, RatesInteractor >

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        handler = Handler(Looper.getMainLooper())
        initAdapter()
        swipe_refresh.setOnRefreshListener {
            presenter.getCurrencyList(null)
        }
    }

    override fun onResume() {
        super.onResume()
        handler?.post(getCurrencies)
    }

    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(getCurrencies)
    }

    private val getCurrencies = object : Runnable {
        override fun run() {
            presenter.getCurrencyList(null)
            handler?.postDelayed(this, 1000)
        }
    }

    override fun scrollToTop() {
        currency_list.smoothScrollToPosition(0)
    }

    override fun updateList(itemCount: Int) {
        adapter?.notifyItemRangeChanged(1, itemCount)
        hideProgress()
    }

    override fun moveBaseItem(positionRemoved: Int) {
        currency_list.post { adapter?.notifyItemMoved(positionRemoved, 0) }
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

    override fun hideProgress() {
        swipe_refresh.isRefreshing = false
    }
}