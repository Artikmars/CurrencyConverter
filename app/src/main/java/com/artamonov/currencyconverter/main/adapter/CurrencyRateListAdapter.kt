package com.artamonov.currencyconverter.main.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.currencyconverter.R
import com.artamonov.currencyconverter.main.networking.models.Rate
import kotlinx.android.synthetic.main.currency_rate_item.view.*
import java.util.ArrayList

class CurrencyRateListAdapter(
    private var mDataSource: AdapterDataSource<Rate>,
    private val listener: OnItemClickListener
) : ListAdapter<Rate, CurrencyRateListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Rate>() {
            override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
                return oldItem.currencyCode == newItem.currencyCode
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(currency: Rate, rateValue: String?, position: Int)
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

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private var currencyItem: Rate? = null

        fun bindItem(item: Rate?) {
            this.currencyItem = item

            view.currency_code.text = currencyItem?.currencyCode
            view.currency_long_name?.text = currencyItem?.currencyLongName
            view.currency_rate_edit?.setText(currencyItem?.rate.toString())

            view.currency_rate_edit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                        if (view.currency_rate_edit.hasFocus()) {
                        currencyItem?.let { listener.onItemClick(currencyItem!!, p0.toString(), adapterPosition) }
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

            view.currency_rate_edit.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) listener.onItemClick(currencyItem!!, currencyItem?.rate.toString(), adapterPosition)
            }

            view.setOnClickListener {
                    listener.onItemClick(currencyItem!!, null, adapterPosition)
            }
        }
        }

    override fun submitList(list: List<Rate>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}
