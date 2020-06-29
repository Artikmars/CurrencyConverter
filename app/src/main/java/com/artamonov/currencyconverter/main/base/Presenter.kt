package com.artamonov.currencyconverter.main.base

interface Presenter<V : BaseView, I : Interactor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?
}