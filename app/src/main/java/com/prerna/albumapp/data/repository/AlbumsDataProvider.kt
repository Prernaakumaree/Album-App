package com.prerna.albumapp.data.repository

import com.prerna.albumapp.data.model.domain.Album
import io.reactivex.Single

interface AlbumsDataProvider {
    fun getAlbums(): Single<List<Album>>
}