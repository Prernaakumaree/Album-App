package com.prerna.albumapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.prerna.albumapp.R
import com.prerna.albumapp.data.model.domain.Album
import kotlinx.android.synthetic.main.album_main.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class AlbumActivity : AppCompatActivity(), AlbumView, KoinComponent {

    private val presenter: AlbumPresenter by inject()

    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_main)

        val llm = LinearLayoutManager(this)
        album_list.layoutManager = llm
        val separator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        album_list.addItemDecoration(separator)

        presenter.bind(this)
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun render(state: AlbumScreenState) {
        when (state) {
            is AlbumScreenState.Loading -> showLoading()
            is AlbumScreenState.DataAvailable -> showAlbums(state.album)
            is AlbumScreenState.Error -> showError(getString(R.string.load_album_error_message))
            is AlbumScreenState.FinishedLoading -> hideLoading()
        }
    }

    private fun showLoading() {
        error.visibility = View.GONE
        album_list.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showAlbums(albums: List<Album>) {
        adapter = AlbumAdapter(albums)
        album_list.adapter = adapter
        album_list.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        error.visibility = View.VISIBLE
        error.setText(message)
    }
}