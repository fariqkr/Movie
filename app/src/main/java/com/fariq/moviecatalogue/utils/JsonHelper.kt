package com.fariq.moviecatalogue.utils

import android.content.Context
import android.util.Log
import com.fariq.moviecatalogue.data.source.remote.reponse.FilmResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<FilmResponse> {
        val list = ArrayList<FilmResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val image = movie.getString("image")
                val desc = movie.getString("desc")
                val genre = movie.getString("genre")
                val year = movie.getInt("year")
                val favorite = movie.getBoolean("favorite")
                val type = movie.getString("type")

                val movieResponse = FilmResponse(id, title, image, desc, genre, year, favorite, type)
                Log.d("mv", movieResponse.toString())
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShows() : List<FilmResponse> {
        val list = ArrayList<FilmResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShows")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getString("id")
                val title = tvShow.getString("title")
                val image = tvShow.getString("image")
                val desc = tvShow.getString("desc")
                val genre = tvShow.getString("genre")
                val year = tvShow.getInt("year")
                val favorite = tvShow.getBoolean("favorite")
                val type = tvShow.getString("type")

                val tvShowResponse = FilmResponse(id, title, image, desc, genre, year, favorite, type)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadMovie(filmId : String) : FilmResponse {
        var result = FilmResponse()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)
                val id = movie.getString("id")
                if (filmId == id) {
                    val title = movie.getString("title")
                    val image = movie.getString("image")
                    val desc = movie.getString("desc")
                    val genre = movie.getString("genre")
                    val year = movie.getInt("year")
                    val favorite = movie.getBoolean("favorite")
                    val type = movie.getString("type")
                    val movieResponse = FilmResponse(id, title, image, desc, genre, year, favorite, type)
                    Log.d("mv", movieResponse.toString())
                    result = movieResponse
                }

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }

}
