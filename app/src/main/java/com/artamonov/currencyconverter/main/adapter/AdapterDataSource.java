package com.artamonov.currencyconverter.main.adapter;

import androidx.annotation.Nullable;

public interface AdapterDataSource<T> {
    int getCount();

    @Nullable
    T get(int position);
}
