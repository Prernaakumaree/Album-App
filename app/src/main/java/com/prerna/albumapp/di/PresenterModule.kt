package com.prerna.albumapp.di

import com.prerna.albumapp.ui.AlbumPresenter
import org.koin.dsl.module

val presenterModule = module {

    factory { AlbumPresenter(get()) }

}