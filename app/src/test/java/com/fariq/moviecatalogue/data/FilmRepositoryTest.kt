package com.fariq.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fariq.moviecatalogue.data.source.local.LocalDataSource
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.data.source.remote.RemoteDataSource
import com.fariq.moviecatalogue.utils.AppExecutors
import com.fariq.moviecatalogue.utils.DataDummy
import com.fariq.moviecatalogue.utils.LiveDataTestUtil
import com.fariq.moviecatalogue.utils.PagedListUtil
import com.fariq.moviecatalogue.vo.Resource

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito


class FilmRepositoryTest {



    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generatesRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generatesRemoteDummyTVShows()
    private val tvShowId = tvShowResponses[0].id


    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        filmRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generatesDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getAllTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generatesDummyTVShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyEntity = MutableLiveData<FilmEntity>()
        dummyEntity.value = DataDummy.generateDummyMovie(movieId)
        Mockito.`when`<LiveData<FilmEntity>>(local.getMovie(movieId)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(filmRepository.getMovie(movieId))
        verify(local).getMovie(movieId)
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data)
    }

    @Test
    fun getTvShow() {
        val dummyEntity = MutableLiveData<FilmEntity>()
        dummyEntity.value = DataDummy.generateDummyMovie(tvShowId)
        Mockito.`when`<LiveData<FilmEntity>>(local.getTvShow(tvShowId)).thenReturn(dummyEntity)

        val tvShowEntity = LiveDataTestUtil.getValue(filmRepository.getTvShow(tvShowId))
        verify(local).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.data)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteMovies()

        val filmEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generatesDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(filmEntities)
        assertEquals(movieResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteTvShows()

        val filmEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generatesDummyTVShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(filmEntities)
        assertEquals(tvShowResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }

}