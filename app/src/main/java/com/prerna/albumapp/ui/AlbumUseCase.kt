package com.prerna.albumapp.ui

import com.prerna.albumapp.data.model.domain.Album
import com.prerna.albumapp.data.repository.AlbumRepository
import io.reactivex.Single
import org.koin.core.KoinComponent

class AlbumUseCase(private val repository: AlbumRepository) : KoinComponent {

    fun execute(): Single<List<Album>> {
        return repository.getAlbums()
    }
}
