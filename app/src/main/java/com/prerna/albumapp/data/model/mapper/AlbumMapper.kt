package com.prerna.albumapp.data.model.mapper

import com.prerna.albumapp.data.model.domain.Album

object AlbumMapper : Mappable<com.prerna.albumapp.data.model.api.Album, Album> {

    override fun map(api: com.prerna.albumapp.data.model.api.Album): Album {
        return Album(
            id = api.id,
            userId = api.userId,
            title = api.title
        )
    }

}