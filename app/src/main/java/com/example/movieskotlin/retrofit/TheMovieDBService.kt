package com.example.movieskotlin.retrofit

import com.example.movieskotlin.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>
}
