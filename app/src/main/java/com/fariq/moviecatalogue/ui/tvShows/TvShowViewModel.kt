package com.fariq.moviecatalogue.ui.tvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fariq.moviecatalogue.data.FilmRepository
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.vo.Resource

class TvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<FilmEntity>>> =
            filmRepository.getAllTvShows()
}