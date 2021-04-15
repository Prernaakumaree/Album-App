package com.prerna.albumapp.di

import com.prerna.albumapp.data.database.AppDatabase
import com.prerna.albumapp.data.repository.AlbumRepository
import org.koin.dsl.module

val dataModule = module {

    single { AlbumRepository(get(), get()) }

    single { AppDatabase(get()).albumDao() }

}