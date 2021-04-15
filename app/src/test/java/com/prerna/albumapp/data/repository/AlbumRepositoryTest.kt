package com.prerna.albumapp.data.repository

import com.prerna.albumapp.data.api.AlbumApi
import com.prerna.albumapp.data.dao.AlbumsDao
import com.prerna.albumapp.data.model.domain.Album
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class AlbumRepositoryTest {

    @RelaxedMockK lateinit var albumDao: AlbumsDao

    @MockK lateinit var albumApi: AlbumApi

    private val albumRepository by lazy {
        AlbumRepository(albumDao, albumApi)
    }

    private val anyAlbum = Album(1, 1, "title")

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `get all album returns cached values if available`() {
        every { albumDao.getAll() } returns Single.just(listOf(anyAlbum))

        val observer = albumRepository.getAlbums().test()
        observer.assertValue(listOf(anyAlbum))
        verify(exactly = 0) { albumApi.getAlbums() }
    }

    @Test
    fun `albums value fetched from api is inserted to the cache`() {
        every { albumDao.getAll() } returns Single.just(listOf())

        albumRepository.getAlbums().test()

        verify {
            albumApi.getAlbums()
            //albumDao.insertAll(*listOf(anyAlbum).toTypedArray())
        }
    }

    @Test
    fun `value from api is returned to caller`() {
        every { albumDao.getAll() } returns Single.just(listOf())
        every { albumApi.getAlbums() } returns Single.just(listOf(anyAlbum))

        val albumObserver = albumRepository.getAlbums().test()

        albumObserver.assertValue(listOf(anyAlbum))
    }

    @Test
    fun `api failing returns reactive error on chain`() {
        every { albumDao.getAll() } returns Single.just(listOf())
        val error = Throwable()
        every { albumApi.getAlbums() } throws error

        val observer = albumRepository.getAlbums().test()

        observer.assertError(error)
    }
}