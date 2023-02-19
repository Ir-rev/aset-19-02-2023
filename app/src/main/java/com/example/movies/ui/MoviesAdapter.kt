package com.example.movies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.databinding.MovieInfoFragmentBinding
import com.example.movies.response.Film

class MoviesAdapter(private val filmsList: List<Film>) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    class MovieHolder(val binding: MovieInfoFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieName = binding.movieName
        val movieImage = binding.movieImage
        val movieContainer = binding.container
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
        holder.movieContainer.setOnClickListener {
            filmsList[position].onClick.invoke()
        }
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

}