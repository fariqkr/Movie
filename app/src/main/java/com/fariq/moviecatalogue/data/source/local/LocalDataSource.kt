package com.fariq.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.data.source.local.room.FilmDao


class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao).apply {
                INSTANCE = this
            }

    }

    fun getAllMovies(): DataSource.Factory<Int, FilmEntity> = mFilmDao.getMovies()

    fun getAllTvShows() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getTvShows()

    fun getFavoriteMovies() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritesMovies()

    fun getFavoriteTvShows() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritesTvShows()

    fun getMovie(movieId: String): LiveData<FilmEntity> =
            mFilmDao.getMovieId(movieId)

    fun getTvShow(tvShowId: String): LiveData<FilmEntity> =
            mFilmDao.getTvShowId(tvShowId)

    fun setFavoriteMovie(film: FilmEntity, newState: Boolean) {
        film.favorite = newState
        mFilmDao.updateMovie(film)
    }

    fun setFavoriteTvShow(film: FilmEntity, newState: Boolean) {
        film.favorite = newState
        mFilmDao.updateTvShow(film)
    }

    fun insertMovies(movies: List<FilmEntity>) {
        mFilmDao.insertMovies(movies)
    }

    fun insertTvShows(tvShows: List<FilmEntity>) {
        mFilmDao.insertTvShows(tvShows)
    }

    fun insertMovie(filmEntity: FilmEntity) {
        mFilmDao.insertMovie(filmEntity)
    }

    fun insertTvShow(filmEntity: FilmEntity) {
        mFilmDao.insertTvShow(filmEntity)
    }

}
