package com.example.movieskotlin.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieskotlin.repository.TheMovieDBRepository
import com.example.movieskotlin.retrofit.models.Movies

class MovieViewModel : ViewModel(){

    private var theMovieDBRepository : TheMovieDBRepository  //esta ase conecta al repositorio para la peticion de las peli pupulares
    private var popularMovies : LiveData<List<Movies>> //variable que contiene las pelis populares que obtenemos

    init {
        theMovieDBRepository = TheMovieDBRepository()
        popularMovies = theMovieDBRepository?.popularMovies()!!
    }

    //Metodo que va a ser consultado desde la UI para devolver las pelis pupus. \
    fun getPopularMovies(): LiveData<List<Movies>>{
        return popularMovies
    }
}