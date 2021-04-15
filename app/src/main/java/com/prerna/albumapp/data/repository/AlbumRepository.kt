package com.prerna.albumapp.data.repository

import com.prerna.albumapp.data.api.AlbumApi
import com.prerna.albumapp.data.dao.AlbumsDao
import com.prerna.albumapp.data.model.domain.Album
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.KoinComponent

class AlbumRepository(
    private val albumsDao: AlbumsDao,
    private val albumApi: AlbumApi
) : KoinComponent, AlbumsDataProvider {

    override fun getAlbums(): Single<List<Album>> {
        return fetchData(
            local = { albumsDao.getAll() },
            remote = { albumApi.getAlbums() },
            insert = { value -> albumsDao.insertAll(*value.toTypedArray()) }
        )
    }

    private fun <T> fetchData(
        local: () -> Single<List<T>>,
        remote: () -> Single<List<T>>,
        insert: (insertValue: List<T>) -> Completable
    ): Single<List<T>> {

        return local.invoke()
            .flatMap {
                if (it.isNotEmpty()) {
                    Single.just(it)
                } else {
                    remote.invoke()
                        .map { value ->
                            insert.invoke(value).subscribe();
                            value
                        }
                }
            }
    }
}
