package com.fariq.moviecatalogue.data

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fariq.moviecatalogue.data.source.NetworkBoundResource
import com.fariq.moviecatalogue.data.source.local.LocalDataSource
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.data.source.remote.ApiResponse
import com.fariq.moviecatalogue.data.source.remote.RemoteDataSource
import com.fariq.moviecatalogue.data.source.remote.reponse.FilmResponse
import com.fariq.moviecatalogue.utils.AppExecutors
import com.fariq.moviecatalogue.utils.DataDummy
import com.fariq.moviecatalogue.utils.LiveDataTestUtil
import com.fariq.moviecatalogue.utils.PagedListUtil
import com.fariq.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class FakeFilmRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : FilmDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<FilmResponse>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in data) {
                    val movie = FilmEntity(response.id,
                        response.title,
                        response.image,
                        response.desc,
                        response.genre,
                        response.year,
                        response.favorite,
                        response.type
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmResponse>>> =
                remoteDataSource.getAllTvShows()

            public override fun saveCallResult(data: List<FilmResponse>) {
                val tvShowList = ArrayList<FilmEntity>()
                for (response in data) {
                    val tvShow = FilmEntity(response.id,
                        response.title,
                        response.image,
                        response.desc,
                        response.genre,
                        response.year,
                        response.favorite,
                        response.type
                    )
                    tvShowList.add(tvShow)
                }
                Log.d("tv", tvShowList.toString())
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun getMovie(filmId: String): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, FilmResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getMovie(filmId)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<FilmResponse>> =
                remoteDataSource.getMovie(filmId)

            override fun saveCallResult(response: FilmResponse) {
                val movie = FilmEntity(response.id,
                    response.title,
                    response.image,
                    response.desc,
                    response.genre,
                    response.year,
                    response.favorite,
                    response.type
                )
                localDataSource.insertMovie(movie)

            }
        }.asLiveData()
    }

    override fun getTvShow(filmId: String): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, FilmResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getTvShow(filmId)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<FilmResponse>> =
                remoteDataSource.getTvShow(filmId)

            override fun saveCallResult(response: FilmResponse) {
                val tvShow = FilmEntity(response.id,
                    response.title,
                    response.image,
                    response.desc,
                    response.genre,
                    response.year,
                    response.favorite,
                    response.type
                )
                localDataSource.insertTvShow(tvShow)

            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(filmEntity: FilmEntity, newState: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(filmEntity, newState) }
    }

    override fun setFavoriteTvShow(filmEntity: FilmEntity, newState: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(filmEntity, newState) }
    }
}