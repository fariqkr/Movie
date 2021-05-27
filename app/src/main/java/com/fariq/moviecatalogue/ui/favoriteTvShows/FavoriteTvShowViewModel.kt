package com.fariq.moviecatalogue.ui.favoriteTvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fariq.moviecatalogue.data.FilmRepository
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity

class FavoriteTvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getFavoriteTvShows(): LiveData<PagedList<FilmEntity>> =
            filmRepository.getFavoriteTvShows()
}