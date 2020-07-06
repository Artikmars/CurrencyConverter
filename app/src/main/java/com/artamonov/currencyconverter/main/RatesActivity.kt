package com.artamonov.currencyconverter.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.adapter.AdapterDataSource
import com.artamonov.currencyconverter.main.adapter.CurrencyRateListAdapter
import com.artamonov.currencyconverter.main.base.BaseActivity
import com.artamonov.currencyconverter.main.networking.models.Rate
import com.artamonov.currencyconverter.main.presenter.RatesViewModel
import com.artamonov.currencyconverter.main.view.RatesView
import kotlinx.android.synthetic.main.activity_main.swipe_refresh
import kotlinx.android.synthetic.main.activity_main.currency_list
import javax.inject.Inject

class RatesActivity : BaseActivity(), RatesView {
    private var adapter: CurrencyRateListAdapter? = null
    private var handler: Handler? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var ratesViewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ratesViewModel = ViewModelProvider(this, viewModelFactory)[RatesViewModel::class.java]

        handler = Handler(Looper.getMainLooper())
        initAdapter()
        swipe_refresh.setOnRefreshListener {
            getCurrencyList()
        }

        getCurrencyList()

        ratesViewModel.user.observe(this, Observer { rateList ->
            updateList(rateList.size)
        })

        ratesViewModel.moveBaseItem.observe(this, Observer { position ->
            moveBaseItem(position)
        })
    }

    private fun getCurrencyList() { ratesViewModel.getCurrencyList(null) }

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
            getCurrencyList()
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
                override fun get(position: Int): Rate {
                    return ratesViewModel.getCurrency(position)
                }
                override val count: Int
                    get() = ratesViewModel.getCurrencyCount()
            }, object : CurrencyRateListAdapter.OnItemClickListener {
                override fun onItemClick(currency: Rate, rateValue: String?, position: Int) {
                    ratesViewModel.rateChanged(currency, rateValue, position)
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