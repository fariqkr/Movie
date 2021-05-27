package com.fariq.moviecatalogue.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fariq.moviecatalogue.data.FilmRepository
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.vo.Resource


class DetailViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    val filmId = MutableLiveData<String>()

    fun setSelectedFilm(filmId: String) {
        this.filmId.value = filmId
    }

    var movie: LiveData<Resource<FilmEntity>> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getMovie(mFilmId)
    }

    var tvShow: LiveData<Resource<FilmEntity>> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getTvShow(mFilmId)
    }

    fun setFavoriteMovie() {
        val filmEntity = movie.value?.data!!
        val newState = !movie.value?.data!!.favorite
        filmRepository.setFavoriteMovie(filmEntity, newState)
    }

    fun setFavoriteTvShow() {
        val filmEntity = tvShow.value?.data!!
        val newState = !tvShow.value?.data!!.favorite
        filmRepository.setFavoriteTvShow(filmEntity, newState)
    }

}