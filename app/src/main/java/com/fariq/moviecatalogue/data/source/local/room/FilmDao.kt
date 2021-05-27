package com.fariq.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM filmEntities WHERE type = 'movie'")
    fun getMovies(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmEntities WHERE type = 'tv show'")
    fun getTvShows(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmEntities WHERE type = 'movie' and favorite = 1")
    fun getFavoritesMovies(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmEntities WHERE type = 'tv show' and favorite = 1")
    fun getFavoritesTvShows(): DataSource.Factory<Int, FilmEntity>

    @Transaction
    @Query("SELECT * FROM filmEntities WHERE id = :movieId")
    fun getMovieId(movieId: String): LiveData<FilmEntity>

    @Query("SELECT * FROM filmEntities WHERE id = :tvShowId")
    fun getTvShowId(tvShowId: String): LiveData<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(films: List<FilmEntity>)

    @Update
    fun updateMovie(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(films: List<FilmEntity>)

    @Update
    fun updateTvShow(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(film: FilmEntity)

}


