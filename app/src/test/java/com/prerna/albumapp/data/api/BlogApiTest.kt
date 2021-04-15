package com.prerna.albumapp.data.api

import com.prerna.albumapp.data.model.api.Album
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class BlogApiTest {

    @RelaxedMockK lateinit var albumService: AlbumService

    @RelaxedMockK lateinit var albumApi : AlbumApi

    private val anyAlbum = Album(1, 1, "title")
    private val anyDomainAlbum = com.prerna.albumapp.data.model.domain.Album(1, 1, "title")

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `get albums contains correct domain models`() {
        every { albumService.getAlbums() } returns Single.just(listOf(anyAlbum))
        every { albumApi.getAlbums() } returns Single.just(listOf(anyDomainAlbum))

        val apiUser = rxValue(albumService.getAlbums()).get(0)
        val users = rxValue(albumApi.getAlbums())

        assertThat(users)
            .hasSize(1)
            .contains(
                com.prerna.albumapp.data.model.domain.Album(
                    id = apiUser.id,
                    userId = apiUser.userId,
                    title = apiUser.title
                )
            )
    }

    private fun <T> rxValue(apiItem: Single<T>): T = apiItem.test().values().get(0)
}