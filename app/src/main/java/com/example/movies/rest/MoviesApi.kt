package com.example.movies.rest

import com.example.movies.response.Film
import com.example.movies.response.FilmsList
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET("sequeniatesttask/films.json")
    fun getFilmsList() : Call<FilmsList>

    @GET("sequeniatesttask/films.json")
    fun getFilm() : Call<Film>
}