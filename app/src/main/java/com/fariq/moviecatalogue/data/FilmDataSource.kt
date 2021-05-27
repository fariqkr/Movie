package com.fariq.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.vo.Resource

interface FilmDataSource {

    fun getAllMovies() : LiveData<Resource<PagedList<FilmEntity>>>
    fun getAllTvShows() : LiveData<Resource<PagedList<FilmEntity>>>
    fun getFavoriteMovies() : LiveData<PagedList<FilmEntity>>
    fun getFavoriteTvShows() : LiveData<PagedList<FilmEntity>>
    fun getMovie(filmId : String) : LiveData<Resource<FilmEntity>>
    fun getTvShow(filmId : String) : LiveData<Resource<FilmEntity>>
    fun setFavoriteMovie(filmEntity: FilmEntity, newState : Boolean)
    fun setFavoriteTvShow(filmEntity: FilmEntity, newState : Boolean)

}