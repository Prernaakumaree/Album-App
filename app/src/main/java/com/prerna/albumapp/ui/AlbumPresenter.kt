package com.prerna.albumapp.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent

class AlbumPresenter(private val albumUseCase: AlbumUseCase) : KoinComponent {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: AlbumView

    fun bind(view: AlbumView) {
        this.view = view
        compositeDisposable.add(loadAlbums())
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun loadAlbums() = albumUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.render(AlbumScreenState.Loading) }
        .doAfterTerminate { view.render(AlbumScreenState.FinishedLoading) }
        .subscribe(
            { view.render(AlbumScreenState.DataAvailable(it)) },
            { view.render(AlbumScreenState.Error(it)) }
        )
}