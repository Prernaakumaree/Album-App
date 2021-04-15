package com.prerna.albumapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prerna.albumapp.data.model.domain.Album
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlbumsDao {

    @Query("SELECT * FROM album ORDER BY title ASC")
    fun getAll(): Single<List<Album>>

    @Query("SELECT * FROM album WHERE id = :id")
    fun getById(id: Int): Single<Album>

    @Query("SELECT * FROM album WHERE userId = :title")
    fun getByTitle(title: String): Single<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg album: Album): Completable
}