package com.example.movieskotlin.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieskotlin.R
import com.example.movieskotlin.retrofit.models.Movies


class MovieListFragment : Fragment() {

    private var columnCount = 2
    private lateinit var movieViewmodel: MovieViewModel
    private lateinit var moviesAdapter: MovieRecyclerViewAdapter
    private var popularMovies: List<Movies> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

        //obtenemos el ViewModel
        movieViewmodel = ViewModelProvider(this).get(MovieViewModel::class.java)
        moviesAdapter = MovieRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = moviesAdapter
            }
        }

        //observer de las peliculas,
        movieViewmodel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
                moviesAdapter.setData(popularMovies)  //actualizamos el adapter con el metodo setData que esta dentro del adapter
        })

        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}