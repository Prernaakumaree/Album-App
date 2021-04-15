package com.prerna.albumapp.data.api

import com.prerna.albumapp.data.api.AlbumService
import com.prerna.albumapp.data.model.api.Album
import io.reactivex.Single

/**
 * Provides some dummy data that you would expect to get back from the [AlbumService]
 */
class StubAlbumService : AlbumService {

    override fun getAlbums(): Single<List<Album>> {
        return Single.just(listOf(
            getUserWithTitle("abc"),
            getUserWithTitle("def"),
            getUserWithTitle("xyz")
        ))
    }

    private fun getUserWithTitle(title: String): Album {
        return Album(1, 1, title)
    }
}