package com.prerna.albumapp.data.api

import com.prerna.albumapp.data.model.api.Album
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

interface AlbumService {

    @GET("/albums")
    fun getAlbums(): Single<List<Album>>

    companion object {
        fun createService(retrofit: Retrofit) = retrofit.create(AlbumService::class.java)
    }

}