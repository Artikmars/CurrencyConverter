package com.artamonov.currencyconverter.main.di

import com.artamonov.currencyconverter.BuildConfig
import com.artamonov.currencyconverter.main.networking.api.API
import com.artamonov.currencyconverter.main.networking.api.ApiHelper
import com.artamonov.currencyconverter.main.networking.api.AppApiHelper
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Singleton
    fun providesNetworkService(@Named("DefaultRetrofit") retrofit: Retrofit): API = retrofit.create(
        API::class.java)

    @Provides
    @Singleton
    @Named("DefaultRetrofit")
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return createRetrofitInstance(okHttpClient, gson)
    }

    private fun createRetrofitInstance(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hiring.revolut.codes/api/android/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttp(): OkHttpClient {
        return createOkHttpClient()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        return okHttpClientBuilder.build()
    }
}
