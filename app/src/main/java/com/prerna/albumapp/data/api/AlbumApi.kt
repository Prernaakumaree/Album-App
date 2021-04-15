package com.prerna.albumapp.data.api

import com.prerna.albumapp.data.model.mapper.AlbumMapper
import com.prerna.albumapp.data.model.mapper.apiToDomain
import com.prerna.albumapp.data.repository.AlbumsDataProvider

class AlbumApi(private val albumService: AlbumService) : AlbumsDataProvider {

    override fun getAlbums() = albumService.getAlbums()
        .map { it.sortedBy { it.title } }
        .map { it.apiToDomain(AlbumMapper) }
}