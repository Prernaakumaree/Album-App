package com.prerna.albumapp.di

import com.prerna.albumapp.ui.AlbumUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { AlbumUseCase(get()) }

}