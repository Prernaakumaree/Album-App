package com.prerna.albumapp.ui

import com.prerna.albumapp.data.model.domain.Album

sealed class AlbumScreenState {
    object Loading : AlbumScreenState()
    class DataAvailable(val album: List<Album>) : AlbumScreenState()
    class Error(val error: Throwable) : AlbumScreenState()
    object FinishedLoading : AlbumScreenState()
}