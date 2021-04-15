package com.prerna.albumapp.album

import com.prerna.albumapp.data.model.domain.Album
import com.prerna.albumapp.ui.AlbumPresenter
import com.prerna.albumapp.ui.AlbumScreenState
import com.prerna.albumapp.ui.AlbumUseCase
import com.prerna.albumapp.ui.AlbumView
import com.prerna.albumapp.rules.RxSchedulerRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumPresenterTest {

    @get:Rule
    val rxRule = RxSchedulerRule()

    @MockK lateinit var albumUseCase: AlbumUseCase
    @RelaxedMockK lateinit var view: AlbumView

    private val anyAlbum = Album(1, 1, "title")

    private val albumPresenter by lazy {
        AlbumPresenter(albumUseCase)
    }

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `binding loads albums`() {
        every { albumUseCase.execute() } returns Single.just(listOf(anyAlbum))

        albumPresenter.bind(view)

        verifyOrder {
            view.render(any<AlbumScreenState.Loading>())
            view.render(any<AlbumScreenState.DataAvailable>())
            view.render(any<AlbumScreenState.FinishedLoading>())
        }
    }

    @Test
    fun `error on binding shows error state after loading`() {
        every { albumUseCase.execute() } returns Single.error(Throwable())

        albumPresenter.bind(view)

        verifyOrder {
            view.render(any<AlbumScreenState.Loading>())
            view.render(any<AlbumScreenState.Error>())
            view.render(any<AlbumScreenState.FinishedLoading>())
        }
    }
}