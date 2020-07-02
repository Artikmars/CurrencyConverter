package com.artamonov.currencyconverter.main.adapter

interface AdapterDataSource<T> {
    val count: Int
    operator fun get(position: Int): T?
}