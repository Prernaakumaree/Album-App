package com.prerna.albumapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prerna.albumapp.R
import com.prerna.albumapp.data.model.domain.Album
import kotlinx.android.synthetic.main.album_row_item.view.*

class AlbumAdapter(
    private val data: List<Album>
) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.album_row_item, parent, false)

        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class AlbumViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Album) {
        view.albumTitle.text = item.title
    }

}