package com.artamonov.currencyconverter.main.view

import com.artamonov.currencyconverter.main.base.BaseView

interface RatesView : BaseView {

    fun scrollToTop()

    fun updateList(itemCount: Int)

    fun moveBaseItem(positionRemoved: Int)
}