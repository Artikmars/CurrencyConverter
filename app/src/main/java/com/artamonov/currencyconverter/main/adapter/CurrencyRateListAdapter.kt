package com.artamonov.currencyconverter.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.networking.models.Rate
import kotlinx.android.synthetic.main.currency_rate_item.view.*

class CurrencyRateListAdapter(
    private val mDataSource: AdapterDataSource<Rate>,
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
        private var contactItem: Rate? = null

        fun bindItem(item: Rate?) {
            this.contactItem = item

            view.currency_code.text = contactItem?.currencyCode
            view.currency_long_name?.text = contactItem?.currencyLongName
            view.currency_rate_edit?.setText(contactItem?.rate.toString())
        }
    }
}
