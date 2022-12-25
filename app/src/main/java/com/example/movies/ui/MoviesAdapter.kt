package com.example.movies.ui

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatDrawableManager.get
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.GenresFragmentBinding.getColorFromResource
import com.example.movies.databinding.GenresFragmentBinding.inflate
import com.example.movies.databinding.MovieInfoFragmentBinding
import com.example.movies.databinding.MovieInfoFragmentBinding.inflate
import com.example.movies.databinding.MovieInfoFragmentBinding.setBindingInverseListener
import com.example.movies.response.Film
import com.example.movies.response.FilmsList
import com.example.movies.ui.moviesInfoFragment.MovieInfoFragment
import java.security.AccessController.getContext

class MoviesAdapter(private val filmsList: List<Film>) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    class MovieHolder(val binding: MovieInfoFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieName = binding.movieName
        val movieImage = binding.movieImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieInfoFragmentBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.movieName.text = filmsList[position].name
            Glide.with(holder.binding.root.context)
                .load(filmsList[position].image_url)
                .into(holder.movieImage)
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

}