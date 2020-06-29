package com.artamonov.currencyconverter.main.base

import com.artamonov.currencyconverter.main.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : BaseView, I : Interactor> internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) : Presenter <V, I> {

    private var view: V? = null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    override fun getView(): V? = view
}
