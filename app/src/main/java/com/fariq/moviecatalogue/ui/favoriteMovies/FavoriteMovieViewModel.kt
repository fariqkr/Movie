package com.fariq.moviecatalogue.ui.favoriteMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fariq.moviecatalogue.data.FilmRepository
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity

class FavoriteMovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<FilmEntity>> =
        filmRepository.getFavoriteMovies()

}