package com.artamonov.currencyconverter.main.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val mMyViewModel: RatesViewModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: ViewModel = if (modelClass == RatesViewModel::class.java) {
            mMyViewModel
        } else {
            throw RuntimeException("unsupported view model class: $modelClass")
        }
        return viewModel as T
    }
}