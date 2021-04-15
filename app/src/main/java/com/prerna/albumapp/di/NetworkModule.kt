package com.prerna.albumapp.di

import com.prerna.albumapp.BuildConfig
import com.prerna.albumapp.data.api.AlbumApi
import com.prerna.albumapp.data.api.AlbumService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single { AlbumService.createService(get()) }

    single { AlbumApi(get()) }

}