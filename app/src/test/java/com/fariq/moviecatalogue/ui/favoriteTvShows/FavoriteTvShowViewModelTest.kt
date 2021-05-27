package com.fariq.moviecatalogue.ui.favoriteTvShows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.fariq.moviecatalogue.data.FilmRepository
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {
    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(filmRepository)
    }

    @Test
    fun `getFavoriteMovies should be success`() {
        val expected = MutableLiveData<PagedList<FilmEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generatesDummyTVShows())

        Mockito.`when`(filmRepository.getFavoriteTvShows()).thenReturn(expected)

        viewModel.getFavoriteTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoriteTvShows().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavoriteMovies should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<FilmEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(filmRepository.getFavoriteTvShows()).thenReturn(expected)

        viewModel.getFavoriteTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavoriteTvShows().value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<FilmEntity>) : PositionalDataSource<FilmEntity>() {
        companion object {
            fun snapshot(items: List<FilmEntity> = listOf()): PagedList<FilmEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<FilmEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<FilmEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}