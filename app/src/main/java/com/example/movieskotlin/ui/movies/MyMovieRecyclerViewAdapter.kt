package com.example.movieskotlin.ui.movies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import coil.load
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.transform.CircleCropTransformation
import com.example.movieskotlin.R
import com.example.movieskotlin.common.Constantes
import com.example.movieskotlin.retrofit.models.Movies
import kotlinx.android.synthetic.main.fragment_movie_list.view.*


class MovieRecyclerViewAdapter() : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var movies: List<Movies> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movies
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()

        holder.ivPhoto.load(Constantes.IMAGE_BASE_URL + item.poster_path) {
            crossfade(true)
            placeholder(R.drawable.ic_cine)
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movies>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivPhoto: ImageView = mView.image_View_photo
        val tvTitle: TextView = mView.text_view_title
        val tvRating: TextView = mView.text_View_rating
    }
}