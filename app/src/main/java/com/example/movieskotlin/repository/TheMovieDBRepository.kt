package com.example.movieskotlin.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.movieskotlin.common.MyApp
import com.example.movieskotlin.retrofit.TheMovieDBClient
import com.example.movieskotlin.retrofit.TheMovieDBService
import com.example.movieskotlin.retrofit.models.Movies
import com.example.movieskotlin.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TheMovieDBRepository {
    var theMovieDBService : TheMovieDBService? = null
    var theMovieDBClient : TheMovieDBClient? = null
    var popularMovies : MutableLiveData<List<Movies>>? = null

    init {
      theMovieDBClient = TheMovieDBClient.instance
        theMovieDBService = theMovieDBClient?.getTheMovieDBService()
        popularMovies = popularMovies()
    }

     fun popularMovies(): MutableLiveData<List<Movies>>? {
         if (popularMovies == null) {
             popularMovies = MutableLiveData<List<Movies>>()
         }

         val call : Call<PopularMoviesResponse>? = theMovieDBService?.getPopularMovies()
         call?.enqueue(object : Callback<PopularMoviesResponse>{
             override fun onResponse(call: Call<PopularMoviesResponse>, response: Response<PopularMoviesResponse>) {
               if (response.isSuccessful) {
                   popularMovies?.value = response.body()?.results
               }
             }

             override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
             }

         })

         return popularMovies
     }
}