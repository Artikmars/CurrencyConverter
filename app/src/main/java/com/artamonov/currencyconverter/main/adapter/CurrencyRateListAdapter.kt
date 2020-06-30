package com.artamonov.currencyconverter.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.networking.models.CurrencyRate

class CurrencyRateListAdapter(
    private val mDataSource: AdapterDataSource<CurrencyRate>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CurrencyRateListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.currency_rate_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feedItem = mDataSource.get(position)
        holder.bindItem(feedItem)
    }

    override fun getItemCount(): Int {
        return mDataSource.count
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        private var contactItem: CurrencyRate? = null

        fun bindItem(item: CurrencyRate?) {
            this.contactItem = item
        }
    }
}
