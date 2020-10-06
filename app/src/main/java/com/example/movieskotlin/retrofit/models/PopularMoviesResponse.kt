package com.example.movieskotlin.retrofit.models

import com.example.movieskotlin.retrofit.models.Movies

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)
