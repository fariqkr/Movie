package com.fariq.moviecatalogue.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fariq.moviecatalogue.data.FilmRepository
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.utils.DataDummy
import com.fariq.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generatesDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(filmRepository)
    }

    @Test
    fun `setSelectedMovie should be success`() {
        viewModel.setSelectedFilm(movieId)
        val expected = MutableLiveData<Resource<FilmEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyMovie(movieId))

        Mockito.`when`(filmRepository.getMovie(movieId)).thenReturn(expected)

        viewModel.movie.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.movie.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setSelectedTvShow should be success`() {
        viewModel.setSelectedFilm(movieId)
        val expected = MutableLiveData<Resource<FilmEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyMovie(movieId))

        Mockito.`when`(filmRepository.getMovie(movieId)).thenReturn(expected)

        viewModel.movie.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.movie.value

        assertEquals(expectedValue, actualValue)
    }
}