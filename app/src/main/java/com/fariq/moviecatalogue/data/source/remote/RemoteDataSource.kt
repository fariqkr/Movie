package com.fariq.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fariq.moviecatalogue.data.source.remote.reponse.FilmResponse
import com.fariq.moviecatalogue.utils.EspressoIdlingResource
import com.fariq.moviecatalogue.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<FilmResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<FilmResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<FilmResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<FilmResponse>>>()
        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        Log.d("tv1", resultTvShow.toString())
        return resultTvShow
    }

    fun getMovie(movieId: String): LiveData<ApiResponse<FilmResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<FilmResponse>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovie(movieId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getTvShow(movieId: String): LiveData<ApiResponse<FilmResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<FilmResponse>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovie(movieId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

//    fun getMovies(callback: LoadMoviesCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            callback.onMoviesReceived(jsonHelper.loadMovies())
//            EspressoIdlingResource.decrement()
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }
//
//    fun getTvShows(callback: LoadTvShowsCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            callback.onTvShowsReceived(jsonHelper.loadTvShows())
//            EspressoIdlingResource.decrement()
//        }, SERVICE_LATENCY_IN_MILLIS)
//    }


//    interface LoadMoviesCallback {
//        fun onMoviesReceived(filmResponses: List<FilmResponse>)
//    }
//
//    interface LoadTvShowsCallback {
//        fun onTvShowsReceived(filmResponses: List<FilmResponse>)
//    }
}
